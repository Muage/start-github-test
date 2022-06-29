package thirdCommit;

import java.text.DecimalFormat;

public class AccountVO {
	DecimalFormat dfWon = new DecimalFormat("#,###��");
	
	private int no;
	private String name;
	private int balance;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "���¹�ȣ : " + no + " | �����ָ� : " + name + " | �ܾ� : " + dfWon.format(balance);
	}
}
