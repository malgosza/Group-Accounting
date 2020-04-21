import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Math.round;

public class StartApp {
    public static void main(String[] args) {
        ArrayList<Integer> persons = new ArrayList<>();
        persons.add(5);
        persons.add(40);
        persons.add(55);

        settlementOfAllPersons(persons);
    }

    private static double wholeAmountDividedIntoOnePerson(ArrayList<Integer> persons) {
        double wholeAmountDividedIntoOnePerson = 0;
        for (int value : persons) wholeAmountDividedIntoOnePerson += value;
        return wholeAmountDividedIntoOnePerson / persons.size();
    }

    private static void amountToBeRefundedOrPaid(double wholeAmountDividedIntoOnePerson, int personToSettleThePayment) {
        double amount = wholeAmountDividedIntoOnePerson - personToSettleThePayment;
        DecimalFormat df = new DecimalFormat("#.##");
        if (amount > 0) {
            System.out.println("Należy dopłacić: " + df.format(amount));
        }
        else if (amount < 0) {
            System.out.println("Kwota do zwrotu dla Ciebie: " +df.format(Math.abs(amount)));
        }
        else {
            System.out.println("Nie musisz ani dopłacać ani oczekiwać na zwrot");
        }
    }

    private static void settlementOfAllPersons( ArrayList<Integer> persons) {
        double wholeAmountDividedIntoOnePerson = wholeAmountDividedIntoOnePerson(persons);
        for (int value: persons){
            amountToBeRefundedOrPaid(wholeAmountDividedIntoOnePerson,value);
        }
    }

}
