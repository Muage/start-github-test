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
	
	//���� �Է� �ޱ�
		public static int input(String title) {
			Scanner scanner = new Scanner(System.in);
			String value = "";
			boolean isNumber = false;
			do {
				System.out.print(title + " : ");
				value = scanner.nextLine();
				isNumber = value.matches("-?\\d+(\\.\\d+)?");
				if(isNumber == false) System.out.println("���ڷ� �Է����ּ���.");
			} while(isNumber == false);
			
			return Integer.parseInt(value);
		}
}
