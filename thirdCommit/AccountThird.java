package thirdCommit;

import java.text.*;
import java.util.*;

public class AccountThird {
	public static void execute() {
		Scanner scanner = new Scanner(System.in);
		DecimalFormat dfWon = new DecimalFormat("#,###��");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean run = true;
		AccountDAO dao = new AccountDAO();
		AccountVO vo = null;
		TradeDAO tdao = new TradeDAO();
		TradeVO tvo = null;
		
		while(run) {
			System.out.println("\n******************** �� �� �� �� ********************");
			System.out.println("---------------------------------------------------");
			System.out.println("1.���»��� | 2.������ȸ | 3.�Ա� | 4.��� | 5.��� | 0.����");
			System.out.println("---------------------------------------------------");
			
			System.out.print("���Ͻô� ������ �������ּ���. > ");
			String menu = scanner.nextLine();
			
			switch(menu) {
			case "0":
				run = false;
				System.out.println("���α׷��� �����մϴ�.");
				
				break;
				
			case "1":
				int newNo = dao.getLastNo() + 100;
				System.out.println("���¹�ȣ : " + newNo);
				
				vo = new AccountVO();
				vo.setNo(newNo);
				
				System.out.print("�����ָ��� �Է����ּ���. : ");
				vo.setName(scanner.nextLine());
				
				boolean isNumber = false;
				String balance = "";
				do {
					System.out.print("�ʱ� �Աݾ��� �Է����ּ���. : ");
					balance = scanner.nextLine();
					
					isNumber = balance.matches("-?\\d+(\\.\\d+)?");
					
					if(!isNumber) System.out.println("�ʱ� �Աݾ��� ���ڷ� �Է����ּ���.\n");
				} while(isNumber == false);
				
				vo.setBalance(Integer.parseInt(balance));
				
				System.out.printf("%s���� ���¸� �����Ͻðڽ��ϱ�? (�� : Y, �ƴϿ� : N) > ", vo.getName());
				String sel = scanner.nextLine();
				
				if (sel.equals("Y") || sel.equals("y") || sel.equals("��")) {
					
					dao.insert(vo);
					tvo = new TradeVO();
					tvo.setNo(newNo);
					tvo.setDate(sdf.format(new Date()));
					tvo.setType("�Ա�");
					tvo.setAmount(Integer.parseInt(balance));
					tdao.insert(tvo);
					
					System.out.printf("%s���� ���°� �����Ǿ����ϴ�.\n", vo.getName());
					System.out.println(vo.toString());
				}
				
				break;
				
			case "2":
				isNumber = false;
				String sno = "";
				do {
					System.out.print("��ȸ�� ���¹�ȣ�� �Է����ּ���. : ");
					sno = scanner.nextLine();
					
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					
					//isNumber�� false�� ����Ǿ��� �ϴ��� if�� �ȿ����� !isNumber = isNumber�� false��� ���� �ؼ� 
					if(!isNumber) System.out.println("���¹�ȣ�� ���ڷ� �Է����ּ���.\n");
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(sno));
				
				if(vo.getName() == null) {
					System.out.println("�Է��Ͻ� ��ȣ�� ��ϵ� ���°� �����ϴ�.");
				} else {
					balance = dfWon.format(vo.getBalance());
					System.out.printf("���¹�ȣ: %d\t�����ָ�: %s\t�ܾ�: %s\n\n", vo.getNo(), vo.getName(), balance);
					
					System.out.println("�ŷ���\t\t����\t�ŷ��ݾ�");
					System.out.println("---------------------------------------------------");
					ArrayList<TradeVO> tarray = tdao.list(Integer.parseInt(sno));
					for(TradeVO t : tarray) {
						System.out.println(t.toString());
					}
				}					
				
				break;
				
			case "3":
				isNumber = false;
				sno = "";
				do {
					System.out.print("�Ա��� ���¹�ȣ�� �Է����ּ���. : ");
					sno = scanner.nextLine();
					
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					 
					if(!isNumber) System.out.println("���¹�ȣ�� ���ڷ� �Է����ּ���.\n"); 
					
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(sno));
				
				if(vo.getName() == null) {
					System.out.println("�Է��Ͻ� ��ȣ�� ��ϵ� ���°� �����ϴ�.");
				} else {
					System.out.println("�����ָ� : " + vo.getName());
					System.out.println("�����ܾ� : " + dfWon.format(vo.getBalance()));
					
					isNumber = false;
					String price;
					do {
						System.out.print("�Ա��Ͻ� �ݾ��� �Է����ּ���. : ");
						price = scanner.nextLine();
						
						isNumber = price.matches("-?\\d+(\\.\\d+)?");
						 
						if(!isNumber) System.out.println("�Ա��Ͻ� �ݾ��� ���ڷ� �Է����ּ���.\n"); 
						
					} while(isNumber == false);
					
					System.out.printf("%s���� ���·� %s �Ա��Ͻðڽ��ϱ�? (�� : Y , �ƴϿ� : N) > ", vo.getName(), dfWon.format(Integer.parseInt(price)));
					sel = scanner.nextLine();
											
					if (sel.equals("Y") || sel.equals("y") || sel.equals("��")) {
						dao.trade(Integer.parseInt(sno), Integer.parseInt(price));
						
						tvo = new TradeVO();
						tvo.setNo(Integer.parseInt(sno));
						tvo.setDate(sdf.format(new Date()));
						tvo.setType("�Ա�");
						tvo.setAmount(Integer.parseInt(price));
						tdao.insert(tvo);
						
						System.out.printf("\n%s���� ���·� %s �ԱݵǾ����ϴ�.\n", vo.getName(), dfWon.format(Integer.parseInt(price)));
						
						int newBalance = vo.getBalance() + Integer.parseInt(price);
						balance = dfWon.format(newBalance);
						
						System.out.printf("���� �ܾ��� %s �Դϴ�.\n", balance);
					}
				}
				
				break;
				
			case "4":
				isNumber = false;
				sno = "";
				do {
					System.out.print("����� ���¹�ȣ�� �Է����ּ���. : ");
					sno = scanner.nextLine();
					
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					 
					if(!isNumber) System.out.println("���¹�ȣ�� ���ڷ� �Է����ּ���.\n"); 
					
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(sno));
				
				if(vo.getName() == null) {
					System.out.println("�Է��Ͻ� ��ȣ�� ��ϵ� ���°� �����ϴ�.");
				} else {
					System.out.println("�����ָ� : " + vo.getName());
					System.out.println("�����ܾ� : " + dfWon.format(vo.getBalance()));
					
					isNumber = false;
					String price;
					do {
						System.out.print("����Ͻ� �ݾ��� �Է����ּ���. : ");
						price = scanner.nextLine();
						isNumber = price.matches("-?\\d+(\\.\\d+)?");
						 
						if(!isNumber) System.out.println("����Ͻ� �ݾ��� ���ڷ� �Է����ּ���.\n"); 
						
					} while(isNumber == false);
					
					System.out.printf("%s���� ���¿��� %s ����Ͻðڽ��ϱ�? (�� : Y , �ƴϿ� : N) > ", vo.getName(), dfWon.format(Integer.parseInt(price)));
					sel = scanner.nextLine();
					
					if (sel.equals("Y") || sel.equals("y") || sel.equals("��")) {
						dao.trade(Integer.parseInt(sno), Integer.parseInt(price) * -1);
						
						tvo = new TradeVO();
						tvo.setNo(Integer.parseInt(sno));
						tvo.setDate(sdf.format(new Date()));
						tvo.setType("���");
						tvo.setAmount(Integer.parseInt(price));
						tdao.insert(tvo);
						
						System.out.printf("\n%s���� ���¿��� %s ��ݵǾ����ϴ�.\n", vo.getName(), dfWon.format(Integer.parseInt(price)));
						
						int newBalance = vo.getBalance() - Integer.parseInt(price);
						balance = dfWon.format(newBalance);
						
						System.out.printf("���� �ܾ��� %s �Դϴ�.\n", balance);
					}
				}
				
				break;
				
			case "5":
				ArrayList<AccountVO> array = dao.list();
				for(AccountVO v : array) {
					balance = dfWon.format(v.getBalance());
					System.out.printf("���¹�ȣ: %d\t�����ָ�: %s\t�ܾ�: %s\n", v.getNo(), v.getName(), balance);
				}
				System.out.printf("\n���� %d���� ���°� ��ϵǾ� �ֽ��ϴ�.\n", array.size());
				
				break;
				
			default:
				System.out.println("0~4�� �� �������ּ���.");
			}
		}
		
	}
}
