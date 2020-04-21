import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class StartApp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<BigDecimal> persons = new ArrayList<>();

        System.out.println("Dodaj wszystkie kwoty do rozliczenia:\nJeśli naciśniesz 0 pokaze sie wynik \n");
        BigDecimal amount=new BigDecimal(scan.nextLine());
        while (amount.compareTo(BigDecimal.ZERO)>0) {
            persons.add(amount);
            System.out.println("Dodaj następną kwotę");
            amount=new BigDecimal(scan.nextLine());
        }
        settlementOfAllPersons(persons);
    }

    private static BigDecimal wholeAmountDividedIntoOnePerson(ArrayList<BigDecimal> persons) {
        BigDecimal wholeAmountDividedIntoOnePerson=BigDecimal.ZERO;
        for (BigDecimal value : persons) wholeAmountDividedIntoOnePerson=wholeAmountDividedIntoOnePerson.add(value);

        return wholeAmountDividedIntoOnePerson.divide(new BigDecimal(persons.size()),2, RoundingMode.HALF_UP);
    }

    private static void amountToBeRefundedOrPaid(BigDecimal wholeAmountDividedIntoOnePerson, BigDecimal personToSettleThePayment) {
        BigDecimal amount = wholeAmountDividedIntoOnePerson.subtract(personToSettleThePayment);
        DecimalFormat df = new DecimalFormat("#.##");
        if (amount.compareTo(BigDecimal.ZERO)>0) {
            System.out.println("Osoba, która zapłaciła do tej pory "+personToSettleThePayment + " musi dopłacić: " + df.format(amount));
        } else if (amount.compareTo(BigDecimal.ZERO)<0) {
            System.out.println("Osobie, która zapłaciła do tej pory "+personToSettleThePayment + " nalezy zwrocic: " + df.format(amount.abs()));
        } else {
            System.out.println("Osoba, która zapłaciła do tej pory "+personToSettleThePayment + " nie musisz ani dopłacać ani oczekiwać na zwrot");
        }
    }

    private static void settlementOfAllPersons(ArrayList<BigDecimal> persons) {
        BigDecimal wholeAmountDividedIntoOnePerson = wholeAmountDividedIntoOnePerson(persons);
        for (BigDecimal value : persons) {
            amountToBeRefundedOrPaid(wholeAmountDividedIntoOnePerson, value);
        }
    }
}
