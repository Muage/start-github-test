package fifthCommit;

import java.util.*;
import helloworld.Main;

public class FacilityManagement {
	public static void execute() {
		Scanner scanner = new Scanner(System.in);
		CampingDAO campingDAO = new CampingDAO();
		CampingVO campingVO = null;
		FacilityDAO facilityDAO = new FacilityDAO();
		
		boolean run = true;
		boolean isFind;
		int switchMenu;
		int code;
		int sel;
		ArrayList<String> farray;
		ArrayList<String> check;
		String[] facility = {"���", "ȭ���", "����", "����", "��������", "������", "������", "������"};
		
		while(run) {
			System.out.println("����������������������������������������������������������������������������������������");
			System.out.println("�� 1.�ü������ �� 2.�ü������� �� 0.�������� ���ư��� ��");
			System.out.println("����������������������������������������������������������������������������������������");
			
			switchMenu = Main.input("�޴��� �������ּ���.");
			
			switch(switchMenu) {
			case 0:
				run = false;
				
				break;
				
			case 1:
				code = Main.input("ķ���� ��ȣ�� �Է����ּ���.");
				campingVO = campingDAO.read(code);
				if(campingVO.getName() == null) {
					System.out.println("�ش� ķ������ �����ϴ�.\n");
					break;
				}
				System.out.println("ķ���� �̸�: " + campingVO.getName());
				System.out.println("ķ���� �ּ�: " + campingVO.getAddress());
				System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
				farray = facilityDAO.list(code);
				check = new ArrayList<>();
				
				
				for(int i=0; i<facility.length; i++) {
					//�ü��� ���� Ȯ��
					
					isFind = false;
					for(String f : farray) {
						if(facility[i].equals(f)) isFind = true;
					}
					if(isFind == true) {
						check.add("O");
					} else {
						check.add("X");
					}
					System.out.printf("[%d] %s \t[ %s ]\n", i, facility[i], check.get(i));
				}
				System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
				sel = Main.input("�ü����� �������ּ���.");
				if(sel == 0) break;
				if(check.get(sel).equals("O")) {
					System.out.println("�̹� ��ϵǾ� �ִ� �ü����Դϴ�.\n");
					break;
				}
				facilityDAO.insert(code, facility[sel]);
				System.out.println(facility[sel] + "��(��) ��ϵǾ����ϴ�.\n");
				
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
				farray = facilityDAO.list(code);
				check = new ArrayList<>();
				
				
				for(int i=0; i<facility.length; i++) {
					//�ü��� ���� Ȯ��
					isFind = false;
					for(String f : farray) {
						if(facility[i].equals(f)) isFind = true;
					}
					if(isFind == true) {
						check.add("O");
					} else {
						check.add("X");
					}
					System.out.printf("[%d] %s \t[ %s ]\n", i, facility[i], check.get(i));
				}
				System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
				sel = Main.input("�ü����� �������ּ���.");
				if(sel == 0) break;
				if(check.get(sel).equals("X")) {
					System.out.println("��ϵ��� ���� �ü����Դϴ�.\n");
					break;
				}
				facilityDAO.delete(code, facility[sel]);
				System.out.println(facility[sel] + "��(��) �����Ǿ����ϴ�.\n");
					
				break;
				
			default:
				System.out.println("0~2�� �� �������ּ���.");
				
			}
		}
	}
}
