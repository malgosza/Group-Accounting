package controller;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Person;
import swing.FormEvent;

public class Controller {

    Database db= new Database();

    public void addPerson(FormEvent ev){
        String name = ev.getName();
        String occupation = ev.getOccupation();
        int ageCatId = ev.getAgeCategory();
        String empCat = ev.getEmploymentCategory();
        boolean isUs=ev.isUsCitizen();
        String taxID=ev.getTaxId();
        String gender = ev.getGender();

        AgeCategory ageCategory;

        switch (ageCatId){
            case 0:
                ageCategory=AgeCategory.child;
                break;
            case 1:
                ageCategory=AgeCategory.adult;
                break;
            case 2:
            default:
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

        Person person= new Person(name, occupation, ageCategory, empCategory, isUs, taxID, gender);
        db.addPerson(person);
    }
}
