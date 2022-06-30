package fourthCommit;

public class SaleVO {
	private int no;
	private String date;
	private int qnt;
	
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
	
	public int getQnt() {
		return qnt;
	}
	
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}

	@Override
	public String toString() {
		return "상품번호: " + no + "\t판매일자: " + date + "\t판매수량: " + qnt + "개";
	}
}
