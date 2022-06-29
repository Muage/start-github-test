package thirdCommit;

import java.text.DecimalFormat;

public class TradeVO {
	private int no;
	private String date;
	private String type;
	private int amount;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		DecimalFormat dfWon = new DecimalFormat("#,###¿ø");
		return date + "\t" + type + "\t" + dfWon.format(amount);
	}
}
