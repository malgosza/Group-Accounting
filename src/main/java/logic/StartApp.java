package logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class StartApp {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        ArrayList<BigDecimal> people = new ArrayList<>();
//
//        System.out.println("Dodaj wszystkie kwoty do rozliczenia:\nJeśli naciśniesz 0 pokaze sie wynik \n");
//        BigDecimal amount=new BigDecimal(scan.nextLine());
//        if (amount.compareTo(BigDecimal.ZERO)==0){
//            System.out.println("Brak osob do rozliczenia");
//        }
//        else{
//            while (amount.compareTo(BigDecimal.ZERO)>0) {
//                people.add(amount);
//                System.out.println("Dodaj następną kwotę:");
//                amount=new BigDecimal(scan.nextLine());
//            }
//            settlementOfAllPeople(people);
//        }
//    }

    public static BigDecimal equallySplittedAmount(ArrayList<BigDecimal> people) {
        BigDecimal wholeAmount=BigDecimal.ZERO;
        for (BigDecimal value : people) {
            wholeAmount=wholeAmount.add(value);
        }
        return wholeAmount.divide(new BigDecimal(people.size()),2, RoundingMode.HALF_UP);
    }

    public static void amountToBeRefundedOrPaid(BigDecimal wholeAmountDividedIntoOnePerson, BigDecimal personToSettleThePayment) {
        BigDecimal amount = wholeAmountDividedIntoOnePerson.subtract(personToSettleThePayment);
        DecimalFormat df = new DecimalFormat("#.##");
        if (amount.compareTo(BigDecimal.ZERO)>0) {
            System.out.println("Osoba, która zapłaciła do tej pory "+personToSettleThePayment.toString() + " musi dopłacić: " + df.format(amount));
        } else if (amount.compareTo(BigDecimal.ZERO)<0) {
            System.out.println("Osobie, która zapłaciła do tej pory "+personToSettleThePayment + " nalezy zwrocic: " + df.format(amount.abs()));
        } else {
            System.out.println("Osoba, która zapłaciła do tej pory "+personToSettleThePayment + " nie musisz ani dopłacać ani oczekiwać na zwrot");
        }
    }

    public static void settlementOfAllPeople(ArrayList<BigDecimal> people) {
        BigDecimal wholeAmount = equallySplittedAmount(people);
        for (BigDecimal value : people) {
            amountToBeRefundedOrPaid(wholeAmount, value);
        }
    }
}
