package secondCommit;

import java.text.DecimalFormat;

public class SaleVO {
	DecimalFormat dfWon = new DecimalFormat("#,###원");
	DecimalFormat dfCount = new DecimalFormat("#,###개");
	
	private int no;
	private String name;
	private int price;
	private int qnt;
	
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
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getQnt() {
		return qnt;
	}
	
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}

	@Override
	public String toString() {
		return "상품번호 : " + no + " | 상품명 : " + name + "\t| 상품단가 : " + dfWon.format(price) + "\t| 판매수량 : " + dfCount.format(qnt);
	}
}
