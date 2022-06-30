package fourthCommit;

import java.text.DecimalFormat;

public class ProductVO {
	DecimalFormat dfWon = new DecimalFormat("#,###만원");
	DecimalFormat dfQnt = new DecimalFormat("#,###개");
	
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
		return "상품번호: " + no + "\t상품이름: " + name + "\t상품단가: " + dfWon.format(price) + "\t상품수량: " + dfQnt.format(qnt);
	}
}
