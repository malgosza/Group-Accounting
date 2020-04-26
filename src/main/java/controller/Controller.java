package controller;

import model.*;
import swing.FormEvent;

import java.util.List;

public class Controller {

    Database db= new Database();

    public List<Person> getPeople(){
        return  db.getPeople();
    }

    public void addPerson(FormEvent ev){
        String name = ev.getName();
        String occupation = ev.getOccupation();
        int ageCatId = ev.getAgeCategory();
        String empCat = ev.getEmploymentCategory();
        boolean isUs=ev.isUsCitizen();
        String taxID=ev.getTaxId();
        String gender = ev.getGender();

        AgeCategory ageCategory=null;

        switch (ageCatId){
            case 0:
                ageCategory=AgeCategory.child;
                break;
            case 1:
                ageCategory=AgeCategory.adult;
                break;
            case 2:
                ageCategory=AgeCategory.senior;
                break;
        }

        EmploymentCategory empCategory;

        if (empCat.equals("employed")){
            empCategory=EmploymentCategory.employed;
        }
        else  if (empCat.equals("self-employed")){
            empCategory=EmploymentCategory.selfEmployed;
        }
        else  if (empCat.equals("unemployed")){
            empCategory=EmploymentCategory.unemployed;
        }
        else {
            empCategory=EmploymentCategory.other;
            System.err.println(empCat);
        }

        Gender genderCat;

        if (gender.equals("male")){
            genderCat= Gender.male;
        }
        else {
            genderCat=Gender.female;
        }

        Person person= new Person(name,
                occupation,
                ageCategory,
                empCategory,
                taxID,
                isUs,
                genderCat);
        db.addPerson(person);
    }
}
