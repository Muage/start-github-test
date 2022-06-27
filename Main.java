package helloworld;

import java.text.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DecimalFormat dfWon = new DecimalFormat("#,###원");
		AccountVO vo = null;
		AccountDAO dao = new AccountDAOImpl();
		ArrayList<AccountVO> array = dao.list();
		boolean run = true;
		int no = 111554000;
		
		while(run) {
			System.out.println("---------------------------------------------------");
			System.out.println("1.계좌생성 | 2.잔액조회 | 3.입금 | 4.출금 | 5.목록 | 0.종료");
			System.out.println("---------------------------------------------------");
			
			System.out.print("원하시는 업무를 선택해주세요. > ");
			int menu = sc.nextInt(); sc.nextLine();
			
			switch(menu) {
			case 0:
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				
				break;
				
			case 1:
				vo = new AccountVO();
				
				System.out.println("안녕하세요, 계좌 생성 업무를 시작하겠습니다.\n");

				System.out.print("계좌주명을 입력하세요. > ");
				vo.setName(sc.nextLine());
				
				System.out.print("초기 입금액을 입력하세요. > ");
				vo.setBalance(sc.nextInt()); sc.nextLine();
				
				dao.insert(vo, array);
				System.out.println("계좌 생성이 완료되었습니다.");
				
				System.out.println("고객님의 계좌번호는 " + no + " 입니다.");
				vo.setNo(no);
				
				System.out.println("감사합니다.\n");
				
				no = no + 111000;
				
				break;
				
			case 2:
				System.out.print("조회하실 계좌번호를 입력하세요. > ");
				int sno = sc.nextInt(); sc.nextLine();
				
				vo = dao.read(sno, array);
				
				if(vo.getName() == null) {
					System.out.println("입력하신 계좌번호에 대한 검색결과가 없습니다.\n계좌번호를 확인해주세요.\n");
				} else {
					System.out.println("계좌주명 : " + vo.getName());
					System.out.println("잔액 : " + vo.getBalance());
					System.out.println("");
				}
				
				break;
				
			case 3:
				System.out.print("입금하실 계좌번호를 입력하세요. > ");
				String ino = sc.nextLine();
				vo = dao.read(Integer.parseInt(ino), array);
				if(vo.getName() == null) {
					System.out.println("입력하신 계좌번호에 대한 검색결과가 없습니다.\n계좌번호를 확인해주세요.\n");
				} else {
					System.out.print("입금하실 금액을 입력해주세요. > ");
					int amount = sc.nextInt(); sc.nextLine();
					dao.trade(Integer.parseInt(ino), amount, array);
					System.out.println(vo.getName() + "님의 (" + ino + ") 계좌로 " + dfWon.format(amount) + "이 입금되었습니다.");
					vo = dao.read(Integer.parseInt(ino), array);
					System.out.println("현재 잔액은 " + dfWon.format(vo.getBalance()) + " 입니다.\n");
				}
				
				break;
				
			case 4:
				System.out.print("출금하실 계좌번호를 입력하세요. > ");
				String ono = sc.nextLine();
				vo = dao.read(Integer.parseInt(ono), array);
				if (vo.getName() == null) {
					System.out.println("입력하신 계좌번호에 대한 검색결과가 없습니다.\n계좌번호를 확인해주세요.\n");
				} else {
					System.out.print("출금하실 금액을 입력해주세요. > ");
					int amount = sc.nextInt(); sc.nextLine();
					
					if (amount <= vo.getBalance() && amount > 0) {
						dao.trade(Integer.parseInt(ono), amount * -1, array);
						System.out.println(vo.getName() + "님의 (" + ono + ") 계좌에서 " + dfWon.format(amount) + "이 출금되었습니다.");
						vo = dao.read(Integer.parseInt(ono), array);
						System.out.println("현재 잔액은 " + dfWon.format(vo.getBalance()) + " 입니다.\n");
					} else if (amount <= 0) {
						System.out.println("입력된 출금액을 확인해주세요.\n");
					} else {
						System.out.println("잔액이 부족합니다.\n");
					}
				}
				
				break;
				
			case 5:
				for(AccountVO v : array) {
					System.out.println(v.toString());
				}
				System.out.println("총 계좌수는 " + array.size() + "개 입니다.\n");
				
				break;
				
			default:
				System.out.println("0~5번 중 선택해주세요.\n");
			}
		}

	}

}
