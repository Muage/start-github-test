package helloworld;

import java.util.Scanner;

import fifthCommit.Camping;
import firstCommit.Account;
import secondCommit.Sale;
import thirdCommit.AccountThird;
import fourthCommit.StudentScore;
import fourthCommit.ProductSale;

public class Main {
	public static void main(String[] args) {
//		Account.execute();
//		Sale.execute();
//		AccountThird.execute();
//		StudentScore.execute();
//		ProductSale.execute();
		Camping.execute();
	}
	
	//숫자 입력 받기
		public static int input(String title) {
			Scanner scanner = new Scanner(System.in);
			String value = "";
			boolean isNumber = false;
			do {
				System.out.print(title + " : ");
				value = scanner.nextLine();
				isNumber = value.matches("-?\\d+(\\.\\d+)?");
				if(isNumber == false) System.out.println("숫자로 입력해주세요.");
			} while(isNumber == false);
			
			return Integer.parseInt(value);
		}
}
