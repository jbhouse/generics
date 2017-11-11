import java.text.*;
import java.util.ArrayList;
import java.util.List;

public class FutureValueApp {

    public static void main(String[] args) {

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
    	int i = 0;
//    	using list for the sake of generics. doubles, ints, (floats etc if we had them) it's all good. all numeric data types are welcome. 
//    	so we have a list of lists.the sublists are just lists of number that represent the outcomes of the calculations
    	List<List<Number>> resultSet = new ArrayList<List<Number>>();
    	System.out.println("Welcome to the Future Value Calculator");
        System.out.println();
        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
        	List<Number> resultArray = new ArrayList<Number>();
            System.out.println("DATA ENTRY");
            double monthlyInvestment = Console.getDouble(
                    "Enter monthly investment: ", 0, 1000);
            double interestRate = Console.getDouble(
                    "Enter yearly interest rate: ", 0, 30);
            int years = Console.getInt(
                    "Enter number of years: ", 0, 100);
            double monthlyInterestRate = interestRate / 12 / 100;
            int months = years * 12;
            double futureValue = calculateFutureValue(
                    monthlyInvestment, monthlyInterestRate, months);
            percent.setMinimumFractionDigits(1);
            String results
                    = "Monthly investment:\t"
                    + currency.format(monthlyInvestment) + "\n"
                    + "Yearly interest rate:\t"
                    + percent.format(interestRate / 100) + "\n"
                    + "Number of years:\t"
                    + years + "\n"
                    + "Future value:\t\t"
                    + currency.format(futureValue) + "\n";
            System.out.println();
            System.out.println("FORMATTED RESULTS");
            System.out.println(results);
            resultArray.add(0, monthlyInvestment);
            resultArray.add(1, interestRate/100.00);
            resultArray.add(2, years);
            resultArray.add(3, futureValue);
            resultSet.add(i, resultArray);
      		i++;
            choice = Console.getString("Continue? (y/n): ");
            System.out.println();
        }
        System.out.println("Future value calculations");
        System.out.println("Inv/Mo.\t Rate\t Years\t Future Value");
        
        for (int j = 0; j < i; j++) {
			System.out.println();
			for (int j2 = 0; j2 < 4; j2++) {
				List<Number> currentArray = resultSet.get(j);
				switch(j2) {
				case 0:
					System.out.print(currency.format(currentArray.get(j2))+"\t ");
					break;
				case 1:
					System.out.print(percent.format(currentArray.get(j2))+"\t   ");
					break;
				case 2:
					System.out.print(currentArray.get(j2)+"\t  ");
					break;
				case 3:
					System.out.print(currency.format(currentArray.get(j2))+"\t");
					break;
				}
			}
		}
    }
    
    public static double calculateFutureValue(double monthlyInvestment,
        double monthlyInterestRate, int months) {
        double futureValue = 0;
        for (int i = 1; i <= months; i++) {
            futureValue = (futureValue + monthlyInvestment)
                        * (1 + monthlyInterestRate);
        }
        return futureValue;
    }
}