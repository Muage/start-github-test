package fifthCommit;

import java.util.*;
import helloworld.Main;

public class OptionManagement {
	public static void execute() {
		Scanner scanner = new Scanner(System.in);
		CampingDAO campingDAO = new CampingDAO();
		CampingVO campingVO = null;
		OptionDAO optionDAO = new OptionDAO();
				
		boolean run = true;
		boolean isFind;
		int switchMenu;
		int code;
		int sel;
		ArrayList<String> tarray;
		ArrayList<String> check;
		String[] option = {"���", "����ķ��", "ī���", "�۷���", "���"};
		
		while(run) {
			System.out.println("����������������������������������������������������������������������������������������������������");
			System.out.println("�� 1.�ü���������� �� 2.�ü����������� �� 0.�������� ���ư��� ��");
			System.out.println("����������������������������������������������������������������������������������������������������");
			
			switchMenu = Main.input("�޴��� �������ּ���.");
			
			switch(switchMenu) {
			case 0:
				run = false;
				
				break;
				
			case 1:
				code = Main.input("ķ���� ��ȣ�� �Է����ּ���.");
				campingVO = campingDAO.read(code);
				if(campingVO.getName() == null) {
					System.out.println("�Է��Ͻ� ��ȣ�� ��ϵ� ķ������ �����ϴ�.\n");
					break;
				}
				System.out.println("ķ���� �̸� : " + campingVO.getName());
				System.out.println("ķ���� �ּ�: " + campingVO.getAddress());
				System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
				tarray = optionDAO.list(code);
				check = new ArrayList<>();
				
				for(int i=0; i<option.length; i++) {
					
					isFind = false;
					for(String t : tarray) {
						if(t.equals(option[i])) isFind = true;
					}
					if(isFind == true) {
							check.add("O");
					} else {
						check.add("X");
					}
					System.out.printf("[%d] %s \t[ %s ]\n", i, option[i], check.get(i));
				}
				System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
				sel = Main.input("������ �������ּ���.");
				if(sel == 0) break;
				if(sel == 0) break;
				if(check.get(sel).equals("O")) {
					System.out.println("�̹� ��ϵǾ� �ִ� �����Դϴ�.\n");
					break;
				}
				//���� ���
				optionDAO.insert(code, option[sel]);
				System.out.println(option[sel] + " ������ ��ϵǾ����ϴ�.\n");
				
				break;
				
			case 2:
				code = Main.input("ķ���� ��ȣ�� �Է����ּ���.");
				campingVO = campingDAO.read(code);
				if(campingVO.getName() == null) {
					System.out.println("�ش� ķ������ �����ϴ�.\n");
					break;
				}
				System.out.println("ķ���� �̸�: " + campingVO.getName());
				System.out.println("ķ���� �ּ�: " + campingVO.getAddress());
				System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
				tarray = optionDAO.list(code);
				check = new ArrayList<>();
				
				
				for(int i=0; i<option.length; i++) {
					//�ü��� ���� Ȯ��
					
					isFind = false;
					for(String t : tarray) {
						if(option[i].equals(t)) isFind = true;
					}
					if(isFind == true) {
						check.add("O");
					} else {
						check.add("X");
					}
					System.out.printf("[%d] %s \t[ %s ]\n", i, option[i], check.get(i));
				}
				System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
				sel = Main.input("������ �������ּ���.");
				if(sel == 0) break;
				if(check.get(sel).equals("X")) {
					System.out.println("��ϵ��� ���� �����Դϴ�.\n");
					break;
				}
				optionDAO.delete(code, option[sel]);
				System.out.println(option[sel] + "������ �����Ǿ����ϴ�.\n");
				
				break;
			
			default:
				System.out.println("0~2�� �� �������ּ���.\n");
			}
		}
	}
}
