package logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;


public class StartApp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<BigDecimal> people = new ArrayList<>();

        System.out.println("Dodaj wszystkie kwoty do rozliczenia:\nJeśli naciśniesz 0 pokaze sie wynik \n");
        BigDecimal amount=new BigDecimal(scan.nextLine());
        if (amount.compareTo(BigDecimal.ZERO)==0){
            System.out.println("Brak osob do rozliczenia");
        }
        else{
            while (amount.compareTo(BigDecimal.ZERO)>0) {
                people.add(amount);
                System.out.println("Dodaj następną kwotę:");
                amount=new BigDecimal(scan.nextLine());
            }
            settlementOfAllPeople(people);
        }
    }

    public static BigDecimal equallySplittedAmount(ArrayList<BigDecimal> people) {
        BigDecimal wholeAmount=BigDecimal.ZERO;
        for (BigDecimal value : people) {
            wholeAmount=wholeAmount.add(value);
        }
        return wholeAmount.divide(new BigDecimal(people.size()),2, RoundingMode.HALF_UP);
    }

    public static SettlementResult amountToBeRefundedOrPaid(BigDecimal wholeAmountDividedIntoOnePerson, BigDecimal personToSettleThePayment) {

        BigDecimal amount = wholeAmountDividedIntoOnePerson.subtract(personToSettleThePayment);

        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            return new SettlementResult(amount, SettlementDirection.niedoplacila);
        } else if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return new SettlementResult(amount, SettlementDirection.zaplacilaZaDuzo);
        }
        return new SettlementResult(amount, SettlementDirection.zero);
    }

    public static void settlementOfAllPeople(ArrayList<BigDecimal> people) {
        BigDecimal wholeAmount = equallySplittedAmount(people);
        for (BigDecimal value : people) {
            amountToBeRefundedOrPaid(wholeAmount, value);
        }
    }
}
