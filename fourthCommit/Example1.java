package fourthCommit;

import java.util.*;

public class Example1 {
	public static void execute() {
		Scanner scanner = new Scanner(System.in);
		
		StudentVO vo = null;
		StudentDAO dao = new StudentDAO();
		ScoreVO scoreVO = null;
		ScoreDAO scoreDAO = new ScoreDAO();
		boolean run = true;
		
		while(run) {
			System.out.println("\n[�������� ���α׷�]");
			System.out.println("����������������������������������������������������������������������������������������������������������");
			System.out.println("�� 1.�л���� �� 2.�л���ȸ �� 3.�л���� �� 4.������� �� 0.���� ��");
			System.out.println("����������������������������������������������������������������������������������������������������������");
			
			System.out.print("���Ͻô� ������ �������ּ���. > ");
			String menu = scanner.nextLine();
			switch(menu) {
			case "0":
				run = false;
				System.out.println("���α׷��� �����մϴ�.");
				
				break;
				
			case "1":
				int newNo = dao.getLastNo() + 100;
				System.out.println("�й�: " + newNo);
				vo = new StudentVO();
				vo.setNo(newNo);
				
				System.out.print("�̸��� �Է����ּ���. : ");
				vo.setName(scanner.nextLine());
				
				System.out.print("�ּҸ� �Է����ּ���. : ");
				vo.setAddress(scanner.nextLine());
				
				dao.insert(vo);
				System.out.printf("\n%d�� %s �л��� ��ϵǾ����ϴ�.\n", newNo, vo.getName());
				
				break;
				
			case "2":
				String sno = "";
				boolean isNumber = false;
				do {
					System.out.print("������ ��ȸ�� �й��� �Է����ּ���. : ");
					sno = scanner.nextLine();
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					if(!isNumber) System.out.println("���� ��ȸ�� �й��� ���ڷ� �Է����ּ���.\n");
				} while(isNumber == false);
				vo = dao.read(Integer.parseInt(sno));
				if(vo.getName() == null) {
					System.out.println("�Է��Ͻ� �й����� ��ϵ� �л��� �����ϴ�.");
				} else {
					//�л� ���� ���
					System.out.printf("�̸�: %s(%s)\n", vo.getName(), vo.getAddress());
					//���� ��� ���
					System.out.println("����������������������������������������������������������������������������������������������");
					System.out.println("������\t������\t������\t������\t������\t�����  ��");
					System.out.println("����������������������������������������������������������������������������������������������");
					ArrayList<ScoreVO> tarray = scoreDAO.list(Integer.parseInt(sno));
					for(ScoreVO v : tarray) {
						v.print();
					}
					System.out.println("����������������������������������������������������������������������������������������������");
				}
					
				break;
				
			case "3":
				System.out.println("");
				
				ArrayList<StudentVO> array = dao.list();
				for(StudentVO v : array) {
					System.out.printf("�й�: %s\t�̸�: %s\t�ּ�: %s\n", v.getNo(), v.getName(), v.getAddress());
				}
					System.out.println("����������������������������������������������������������������������������������������������������������");
					System.out.printf("���� ��ϵ� �л��� �� %d�� �Դϴ�.\n", array.size());
				
				break;
				
			case "4":
				String no = "";
				isNumber = false;
				do {
					System.out.print("������ ����� �й��� �Է����ּ���. : ");
					no = scanner.nextLine();
					isNumber = no.matches("-?\\d+(\\.\\d+)?");
					
					if(!isNumber) System.out.println("���� ����� �й��� ���ڷ� �Է����ּ���.\n");
				} while(isNumber == false);
				
				//�л� ���� �б�
				vo = dao.read(Integer.parseInt(no));
				if(vo.getName() == null) {
					System.out.println("�Է��Ͻ� �й����� ��ϵ� �л��� �����ϴ�.");
				} else {
					//�л� ���� ���
					System.out.printf("�̸�: %s(%s)\n", vo.getName(), vo.getAddress());
					
					//���� ��� ���
					scoreVO = scoreDAO.read(Integer.parseInt(no));
					if(scoreVO.getType() == null) {
						System.out.printf("%s �л��� ��ϵ� ������ �����ϴ�.\n", vo.getName());
						System.out.printf("%s �л��� ������ ����Ͻðڽ��ϱ�? ( �� : Y , �ƴϿ� : N) > ", vo.getName());
						String sel = scanner.nextLine();
						
						String type = "";
						String kor = "";
						String eng = "";
						String mat = "";
						if (sel.equals("Y") || sel.equals("y") || sel.equals("��")) {
							System.out.println("");
							System.out.print("����(�߰�/�⸻/����)�� �Է����ּ���. : ");
							type = scanner.nextLine();
							
							isNumber = false;
							do {
								System.out.print("���� ������ �Է����ּ���. : ");
								kor = scanner.nextLine();
								isNumber = kor.matches("-?\\d+(\\.\\d+)?");
								
								if(!isNumber) System.out.println("���� ������ ���ڷ� �Է����ּ���.\n");
							} while(isNumber == false);
							
							isNumber = false;
							do {
								System.out.print("���� ������ �Է����ּ���. : ");
								eng = scanner.nextLine();
								isNumber = eng.matches("-?\\d+(\\.\\d+)?");
								
								if(!isNumber) System.out.println("���� ������ ���ڷ� �Է����ּ���.\n");
							} while(isNumber == false);
							
							isNumber = false;
							do {
								System.out.print("���� ������ �Է����ּ���. : ");
								mat = scanner.nextLine();
								isNumber = mat.matches("-?\\d+(\\.\\d+)?");
								
								if(!isNumber) System.out.println("���� ������ ���ڷ� �Է����ּ���.\n");
							} while(isNumber == false);
							
							scoreVO = new ScoreVO();
							scoreVO.setNo(Integer.parseInt(no));
							scoreVO.setType(type);
							scoreVO.setKor(Integer.parseInt(kor));
							scoreVO.setEng(Integer.parseInt(eng));
							scoreVO.setMat(Integer.parseInt(mat));
							
							scoreDAO.insert(scoreVO);
							System.out.printf("%s �л��� %s ������ ��ϵǾ����ϴ�.\n", vo.getName() , type);
							
							break;
						}
					}else {
						System.out.println("����������������������������������������������������������������������������������������������");
						System.out.println("������\t������\t������\t������\t������\t�����  ��");
						System.out.println("����������������������������������������������������������������������������������������������");
						ArrayList<ScoreVO> tarray = scoreDAO.list(Integer.parseInt(no));
						for(ScoreVO v : tarray) {
							v.print();
						}
						System.out.println("����������������������������������������������������������������������������������������������");
					}
				
					//���� �Է�
					System.out.print("����(�߰�/�⸻/����)�� �Է����ּ���. : ");
					String type = scanner.nextLine();
					
					//���� ����
					String kor = "";
					isNumber = false;
					do {
						System.out.print("���� ������ �Է����ּ���. : ");
						kor = scanner.nextLine();
						isNumber = kor.matches("-?\\d+(\\.\\d+)?");
						
						if(!isNumber) System.out.println("���� ������ ���ڷ� �Է����ּ���.\n");
					} while(isNumber == false);
					
					//���� ����
					String eng = "";
					isNumber = false;
					do {
						System.out.print("���� ������ �Է����ּ���. : ");
						eng = scanner.nextLine();
						isNumber = eng.matches("-?\\d+(\\.\\d+)?");
						
						if(!isNumber) System.out.println("���� ������ ���ڷ� �Է����ּ���.\n");
					} while(isNumber == false);
					
					//���� ����
					String mat = "";
					isNumber = false;
					do {
						System.out.print("���� ������ �Է����ּ���. : ");
						mat = scanner.nextLine();
						isNumber = mat.matches("-?\\d+(\\.\\d+)?");
						
						if(!isNumber) System.out.println("���� ������ ���ڷ� �Է����ּ���.\n");
					} while(isNumber == false);
					
					scoreVO = new ScoreVO();
					scoreVO.setNo(Integer.parseInt(no));
					scoreVO.setType(type);
					scoreVO.setKor(Integer.parseInt(kor));
					scoreVO.setEng(Integer.parseInt(eng));
					scoreVO.setMat(Integer.parseInt(mat));
					
					scoreDAO.insert(scoreVO);
					System.out.printf("%s �л��� %s ������ ��ϵǾ����ϴ�.\n", vo.getName() , type);
				}
				
//				System.out.printf("%s �л��� %s ������ ��ϵǾ����ϴ�.", , type);
				
				break;
				
			default:
				System.out.println("0~4�� �� �������ּ���.");
			}
		}
	}
}
