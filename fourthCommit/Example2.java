package fourthCommit;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Example2 {
	public static void execute() {
		Scanner scanner = new Scanner(System.in);
		DecimalFormat dfWon = new DecimalFormat("#,###����");
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		
		ProductVO productVO = null;
		ProductDAO productDAO = new ProductDAO();
		SaleVO saleVO = null;
		SaleDAO saleDAO = new SaleDAO();
		boolean run = true;
		
		while(run) {
			System.out.println("\n\t\t    [��ǰ���� ���α׷�]");
			System.out.println("����������������������������������������������������������������������������������������������������������");
			System.out.println("�� 1.��ǰ��� �� 2.��ǰ��ȸ �� 3.��ǰ��� �� 4.������ �� 0.���� ��");
			System.out.println("����������������������������������������������������������������������������������������������������������");
			
			System.out.print("���Ͻô� ������ �������ּ���. > ");
			String menu = scanner.nextLine();
			switch(menu) {
			case "0":
				run = false;
				System.out.println("���α׷��� �����մϴ�.");
				
				break;
				
			case "1":
				int newCode = productDAO.getLastNo() + 100;
				System.out.println("��ǰ��ȣ: " + newCode);
				productVO = new ProductVO();
				productVO.setNo(newCode);
				
				System.out.print("��ǰ �̸��� �Է����ּ���. : ");
				productVO.setName(scanner.nextLine());
				
				System.out.print("��ǰ �ܰ��� �Է����ּ���. : ");
				productVO.setPrice(scanner.nextInt()); scanner.nextLine();
				
				System.out.print("��� ������ �Է����ּ���. : ");
				productVO.setQnt(scanner.nextInt()); scanner.nextLine();
				
				productDAO.insert(productVO);
				System.out.printf("%s ��ǰ�� ��ϵǾ����ϴ�.\n", productVO.getName());
				
				break;
				
			case "2":
				String sno = "";
				boolean isNumber = false;
				do {
					System.out.print("��ȸ�� ��ǰ��ȣ�� �Է����ּ���. : ");
					sno = scanner.nextLine();
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					if(!isNumber) System.out.println("��ǰ ��ȸ�� ��ȣ�� ���ڷ� �Է����ּ���.\n");
				} while(isNumber == false);
					
				productVO = productDAO.read(Integer.parseInt(sno));
				System.out.println("");
				
				if(productVO.getName() == null) {
					System.out.println("�Է��Ͻ� ��ȣ�� ��ϵ� ��ǰ�� �����ϴ�.\n");
				} else {
					System.out.println(productVO.toString());
					//��ǰ �Ǹ� ���
					ArrayList<SaleVO> sarray = saleDAO.list(Integer.parseInt(sno));
					if(sarray.size() == 0) {
						System.out.println("");
						System.out.println("�Ǹ��� ��ǰ�� �����ϴ�.");
					} else {
						System.out.println("");
						for(SaleVO v : sarray) {
							System.out.println(v.toString());
						}
					}
				}
					
				break;
				
			case "3":
				ArrayList<ProductVO> array = productDAO.list();
				System.out.println("������������������������������������������������������������������������������������������");
				System.out.println("����ǰ��ȣ\t����ǰ�̸�\t����ǰ�ܰ�\t��������\t��\t�ݾ� ��");
				System.out.println("������������������������������������������������������������������������������������������");
				for(ProductVO v : array) {
					int sum = v.getPrice() * v.getQnt();
					String strSum = dfWon.format(sum);
					String price = dfWon.format(v.getPrice());
					
					System.out.printf("��%d\t��%s\t��%s\t��%d��\t��%10s��\n", v.getNo(), v.getName(), price, v.getQnt(), strSum);
				}
				System.out.println("������������������������������������������������������������������������������������������");

				System.out.printf("���� ��ϵǾ� �ִ� ��ǰ�� �� %d�� �Դϴ�.\n", array.size());
				
				break;
				
			case "4":
				sno = "";
				isNumber = false;
				do {
					System.out.print("������ ����� ��ǰ��ȣ�� �Է����ּ���. : ");
					sno = scanner.nextLine();
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					if(!isNumber) System.out.println("������ ����� ��ȣ�� ���ڷ� �Է����ּ���.\n");
				} while(isNumber == false);
					
				productVO = productDAO.read(Integer.parseInt(sno));
				System.out.println("");
				
				if(productVO.getName() == null) {
					System.out.println("�Է��Ͻ� ��ȣ�� ��ϵ� ��ǰ�� �����ϴ�.\n");
				} else {
					System.out.println(productVO.toString());
					
					//��ǰ �Ǹ� ���
					ArrayList<SaleVO> sarray = saleDAO.list(Integer.parseInt(sno));
					if(sarray.size() == 0) {
						System.out.println("");
						System.out.println("�Ǹ��� ��ǰ�� �����ϴ�.");
						System.out.printf("���� ��� ������ %d�� �Դϴ�. \n", productVO.getQnt());
					} else {
						System.out.println("");
						for(SaleVO v : sarray) {
							System.out.println(v.toString());
						}
					}
					
					System.out.println("");
					saleVO = new SaleVO();
					saleVO.setNo(Integer.parseInt(sno));
					saleVO.setDate(sdf.format(new Date()));
					System.out.println("�Ǹ����� : " + saleVO.getDate());

					System.out.print("�Ǹż����� �Է����ּ���. : ");
					saleVO.setQnt(scanner.nextInt()); scanner.nextLine();
					saleDAO.insert(saleVO);
					
					//������ - �Ǹż���
					productDAO.update(Integer.parseInt(sno), saleVO.getQnt());
					
					System.out.printf("\n%s ��ǰ�� ������ ��ϵǾ����ϴ�.\n", productVO.getName());
					break;
				}
				
				break;
				
			default:
				System.out.println("0~4�� �� �������ּ���.");
			}
		}
	}
}
