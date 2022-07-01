package fifthCommit;

import java.util.*;
import helloworld.Main;

public class Camping {
	public static void execute() {
		Scanner scanner = new Scanner(System.in);
		CampingDAO campingDAO = new CampingDAO();
		CampingVO campingVO = null;
		OptionDAO typeDAO = new OptionDAO();
		FacilityDAO facilityDAO = new FacilityDAO();
				
		boolean run = true;
		String switchMenu;
		int newCode;
		int code;
		
		while(run) {
			System.out.println("\n��������������������������������������������������������������������������������������������������������������������������������������������");
			System.out.println("��\t\t            ķ������� ���α׷�            \t\t     ��");
			System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
			System.out.println("�� 1.ķ������ �� 2.ķ������ȸ �� 3.ķ������ �� 4.�ü������� �� 5.�������� �� 0.���� ��");
			System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
			
			System.out.print("���Ͻô� ������ �������ּ���. > ");
			switchMenu = scanner.nextLine();
			
			switch(switchMenu) {
			case "0":
				run = false;
				System.out.println("���α׷��� �����մϴ�.");
				
				break;
				
			case "1":
				newCode = campingDAO.getLastCode() + 100;
				System.out.println("ķ���� �ڵ�: " + newCode);
				campingVO = new CampingVO();
				campingVO.setCode(newCode);
				System.out.print("ķ���� �̸��� �Է����ּ���. : ");
				campingVO.setName(scanner.nextLine());
				
				System.out.print("ķ���� �ּҸ� �Է����ּ���. : ");
				campingVO.setAddress(scanner.nextLine());
				
				//���Ͽ� ����
				campingDAO.insert(campingVO);
				System.out.printf("%s ķ������ ��ϵǾ����ϴ�.\n", campingVO.getName());

				break;
				
			case "2":
				code = Main.input("��ȸ�� �ڵ带 �Է����ּ���.");
				campingVO = campingDAO.read(code);
				if(campingVO.getName() == null) {
					System.out.println("�Է��Ͻ� �ڵ�� ��ϵ� ķ������ �����ϴ�.");
				} else {
					System.out.println("ķ���� �̸�: " + campingVO.getName());
					System.out.println("ķ���� �ּ�: " + campingVO.getAddress());
					System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
					
					//ķ���� Type ���
					ArrayList<String> tarray = typeDAO.list(code);
					System.out.print("ķ���� �ɼ�:");
					if(tarray.size() == 0) System.out.println(" ����");
					for(String t : tarray) {
						System.out.printf(" %s |", t);
					}
					System.out.println("");
					
					//ķ���� �ü��� ���
					ArrayList<String> farray = facilityDAO.list(code);
					System.out.print("ķ���� �ü���:");
					if(farray.size() == 0) System.out.println(" ����");
					for(String f : farray) {
						System.out.printf(" %s |", f);
					}
					System.out.println("");
				}
					
				break;
				
			case "3":				
				ArrayList<CampingVO> carray = campingDAO.list();
				for(CampingVO c : carray) {
					System.out.println("�ڵ�\t\t�̸�\t\t�ּ�");
					System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
					System.out.printf("%d\t\t%s\t\t%s\n", c.getCode(), c.getName(), c.getAddress());
					System.out.println("");
					
					//�� ķ���庰 Type ���
					ArrayList<String> tarray = typeDAO.list(c.getCode());
					System.out.println("[�ɼ�]");
					if(tarray.size() == 0) System.out.println("����");
					for(String t : tarray) {
						System.out.printf("�� %s\n", t);
					}
					System.out.println("");
					
					//�� ķ���庰 �ü��� ���
					ArrayList<String> farray = facilityDAO.list(c.getCode());
					System.out.println("[�ü���]");
					if(farray.size() == 0) System.out.println("����");
					for(String f : farray) {
						System.out.printf("- %s\n", f);
					}
					System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������");
				}
				System.out.println("��ϵ� ķ���� �� : " + carray.size());
				
				break;
				
			case "4":
				FacilityManagement.execute();
				
				break;
			
			case "5":
				OptionManagement.execute();
				
				break;
				
			default:
				System.out.println("0~5�� �� �������ּ���.");
			}
		}
	}
}
