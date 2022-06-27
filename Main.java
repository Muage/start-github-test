package helloworld;

import java.text.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DecimalFormat dfWon = new DecimalFormat("#,###��");
		AccountVO vo = null;
		AccountDAO dao = new AccountDAOImpl();
		ArrayList<AccountVO> array = dao.list();
		boolean run = true;
		int no = 111554000;
		
		while(run) {
			System.out.println("---------------------------------------------------");
			System.out.println("1.���»��� | 2.�ܾ���ȸ | 3.�Ա� | 4.��� | 5.��� | 0.����");
			System.out.println("---------------------------------------------------");
			
			System.out.print("���Ͻô� ������ �������ּ���. > ");
			int menu = sc.nextInt(); sc.nextLine();
			
			switch(menu) {
			case 0:
				run = false;
				System.out.println("���α׷��� �����մϴ�.\n");
				
				break;
				
			case 1:
				vo = new AccountVO();
				
				System.out.println("�ȳ��ϼ���, ���� ���� ������ �����ϰڽ��ϴ�.\n");

				System.out.print("�����ָ��� �Է��ϼ���. > ");
				vo.setName(sc.nextLine());
				
				System.out.print("�ʱ� �Աݾ��� �Է��ϼ���. > ");
				vo.setBalance(sc.nextInt()); sc.nextLine();
				
				dao.insert(vo, array);
				System.out.println("���� ������ �Ϸ�Ǿ����ϴ�.");
				
				System.out.println("������ ���¹�ȣ�� " + no + " �Դϴ�.");
				vo.setNo(no);
				
				System.out.println("�����մϴ�.\n");
				
				no = no + 111000;
				
				break;
				
			case 2:
				System.out.print("��ȸ�Ͻ� ���¹�ȣ�� �Է��ϼ���. > ");
				int sno = sc.nextInt(); sc.nextLine();
				
				vo = dao.read(sno, array);
				
				if(vo.getName() == null) {
					System.out.println("�Է��Ͻ� ���¹�ȣ�� ���� �˻������ �����ϴ�.\n���¹�ȣ�� Ȯ�����ּ���.\n");
				} else {
					System.out.println("�����ָ� : " + vo.getName());
					System.out.println("�ܾ� : " + vo.getBalance());
					System.out.println("");
				}
				
				break;
				
			case 3:
				System.out.print("�Ա��Ͻ� ���¹�ȣ�� �Է��ϼ���. > ");
				String ino = sc.nextLine();
				vo = dao.read(Integer.parseInt(ino), array);
				if(vo.getName() == null) {
					System.out.println("�Է��Ͻ� ���¹�ȣ�� ���� �˻������ �����ϴ�.\n���¹�ȣ�� Ȯ�����ּ���.\n");
				} else {
					System.out.print("�Ա��Ͻ� �ݾ��� �Է����ּ���. > ");
					int amount = sc.nextInt(); sc.nextLine();
					dao.trade(Integer.parseInt(ino), amount, array);
					System.out.println(vo.getName() + "���� (" + ino + ") ���·� " + dfWon.format(amount) + "�� �ԱݵǾ����ϴ�.");
					vo = dao.read(Integer.parseInt(ino), array);
					System.out.println("���� �ܾ��� " + dfWon.format(vo.getBalance()) + " �Դϴ�.\n");
				}
				
				break;
				
			case 4:
				System.out.print("����Ͻ� ���¹�ȣ�� �Է��ϼ���. > ");
				String ono = sc.nextLine();
				vo = dao.read(Integer.parseInt(ono), array);
				if (vo.getName() == null) {
					System.out.println("�Է��Ͻ� ���¹�ȣ�� ���� �˻������ �����ϴ�.\n���¹�ȣ�� Ȯ�����ּ���.\n");
				} else {
					System.out.print("����Ͻ� �ݾ��� �Է����ּ���. > ");
					int amount = sc.nextInt(); sc.nextLine();
					
					if (amount <= vo.getBalance() && amount > 0) {
						dao.trade(Integer.parseInt(ono), amount * -1, array);
						System.out.println(vo.getName() + "���� (" + ono + ") ���¿��� " + dfWon.format(amount) + "�� ��ݵǾ����ϴ�.");
						vo = dao.read(Integer.parseInt(ono), array);
						System.out.println("���� �ܾ��� " + dfWon.format(vo.getBalance()) + " �Դϴ�.\n");
					} else if (amount <= 0) {
						System.out.println("�Էµ� ��ݾ��� Ȯ�����ּ���.\n");
					} else {
						System.out.println("�ܾ��� �����մϴ�.\n");
					}
				}
				
				break;
				
			case 5:
				for(AccountVO v : array) {
					System.out.println(v.toString());
				}
				System.out.println("�� ���¼��� " + array.size() + "�� �Դϴ�.\n");
				
				break;
				
			default:
				System.out.println("0~5�� �� �������ּ���.\n");
			}
		}

	}

}
