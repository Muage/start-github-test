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
		return "��ǰ��ȣ: " + no + "\t�Ǹ�����: " + date + "\t�Ǹż���: " + qnt + "��";
	}
}
