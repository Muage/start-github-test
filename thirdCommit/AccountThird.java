package thirdCommit;

import java.text.*;
import java.util.*;

public class AccountThird {
	public static void execute() {
		Scanner scanner = new Scanner(System.in);
		DecimalFormat dfWon = new DecimalFormat("#,###원");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean run = true;
		AccountDAO dao = new AccountDAO();
		AccountVO vo = null;
		TradeDAO tdao = new TradeDAO();
		TradeVO tvo = null;
		
		while(run) {
			System.out.println("\n******************** 계 좌 관 리 ********************");
			System.out.println("---------------------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌조회 | 3.입금 | 4.출금 | 5.목록 | 0.종료");
			System.out.println("---------------------------------------------------");
			
			System.out.print("원하시는 업무를 선택해주세요. > ");
			String menu = scanner.nextLine();
			
			switch(menu) {
			case "0":
				run = false;
				System.out.println("프로그램을 종료합니다.");
				
				break;
				
			case "1":
				int newNo = dao.getLastNo() + 100;
				System.out.println("계좌번호 : " + newNo);
				
				vo = new AccountVO();
				vo.setNo(newNo);
				
				System.out.print("계좌주명을 입력해주세요. : ");
				vo.setName(scanner.nextLine());
				
				boolean isNumber = false;
				String balance = "";
				do {
					System.out.print("초기 입금액을 입력해주세요. : ");
					balance = scanner.nextLine();
					
					isNumber = balance.matches("-?\\d+(\\.\\d+)?");
					
					if(!isNumber) System.out.println("초기 입금액을 숫자로 입력해주세요.\n");
				} while(isNumber == false);
				
				vo.setBalance(Integer.parseInt(balance));
				
				System.out.printf("%s님의 계좌를 생성하시겠습니까? (예 : Y, 아니요 : N) > ", vo.getName());
				String sel = scanner.nextLine();
				
				if (sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
					
					dao.insert(vo);
					tvo = new TradeVO();
					tvo.setNo(newNo);
					tvo.setDate(sdf.format(new Date()));
					tvo.setType("입금");
					tvo.setAmount(Integer.parseInt(balance));
					tdao.insert(tvo);
					
					System.out.printf("%s님의 계좌가 생성되었습니다.\n", vo.getName());
					System.out.println(vo.toString());
				}
				
				break;
				
			case "2":
				isNumber = false;
				String sno = "";
				do {
					System.out.print("조회할 계좌번호를 입력해주세요. : ");
					sno = scanner.nextLine();
					
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					
					//isNumber가 false로 선언되었다 하더라도 if문 안에서는 !isNumber = isNumber가 false라면 으로 해석 
					if(!isNumber) System.out.println("계좌번호를 숫자로 입력해주세요.\n");
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(sno));
				
				if(vo.getName() == null) {
					System.out.println("입력하신 번호로 등록된 계좌가 없습니다.");
				} else {
					balance = dfWon.format(vo.getBalance());
					System.out.printf("계좌번호: %d\t계좌주명: %s\t잔액: %s\n\n", vo.getNo(), vo.getName(), balance);
					
					System.out.println("거래일\t\t내용\t거래금액");
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
					System.out.print("입금할 계좌번호를 입력해주세요. : ");
					sno = scanner.nextLine();
					
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					 
					if(!isNumber) System.out.println("계좌번호를 숫자로 입력해주세요.\n"); 
					
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(sno));
				
				if(vo.getName() == null) {
					System.out.println("입력하신 번호로 등록된 계좌가 없습니다.");
				} else {
					System.out.println("계좌주명 : " + vo.getName());
					System.out.println("현재잔액 : " + dfWon.format(vo.getBalance()));
					
					isNumber = false;
					String price;
					do {
						System.out.print("입금하실 금액을 입력해주세요. : ");
						price = scanner.nextLine();
						
						isNumber = price.matches("-?\\d+(\\.\\d+)?");
						 
						if(!isNumber) System.out.println("입금하실 금액을 숫자로 입력해주세요.\n"); 
						
					} while(isNumber == false);
					
					System.out.printf("%s님의 계좌로 %s 입금하시겠습니까? (예 : Y , 아니요 : N) > ", vo.getName(), dfWon.format(Integer.parseInt(price)));
					sel = scanner.nextLine();
											
					if (sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
						dao.trade(Integer.parseInt(sno), Integer.parseInt(price));
						
						tvo = new TradeVO();
						tvo.setNo(Integer.parseInt(sno));
						tvo.setDate(sdf.format(new Date()));
						tvo.setType("입금");
						tvo.setAmount(Integer.parseInt(price));
						tdao.insert(tvo);
						
						System.out.printf("\n%s님의 계좌로 %s 입금되었습니다.\n", vo.getName(), dfWon.format(Integer.parseInt(price)));
						
						int newBalance = vo.getBalance() + Integer.parseInt(price);
						balance = dfWon.format(newBalance);
						
						System.out.printf("현재 잔액은 %s 입니다.\n", balance);
					}
				}
				
				break;
				
			case "4":
				isNumber = false;
				sno = "";
				do {
					System.out.print("출금할 계좌번호를 입력해주세요. : ");
					sno = scanner.nextLine();
					
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					 
					if(!isNumber) System.out.println("계좌번호를 숫자로 입력해주세요.\n"); 
					
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(sno));
				
				if(vo.getName() == null) {
					System.out.println("입력하신 번호로 등록된 계좌가 없습니다.");
				} else {
					System.out.println("계좌주명 : " + vo.getName());
					System.out.println("현재잔액 : " + dfWon.format(vo.getBalance()));
					
					isNumber = false;
					String price;
					do {
						System.out.print("출금하실 금액을 입력해주세요. : ");
						price = scanner.nextLine();
						isNumber = price.matches("-?\\d+(\\.\\d+)?");
						 
						if(!isNumber) System.out.println("출금하실 금액을 숫자로 입력해주세요.\n"); 
						
					} while(isNumber == false);
					
					System.out.printf("%s님의 계좌에서 %s 출금하시겠습니까? (예 : Y , 아니요 : N) > ", vo.getName(), dfWon.format(Integer.parseInt(price)));
					sel = scanner.nextLine();
					
					if (sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
						dao.trade(Integer.parseInt(sno), Integer.parseInt(price) * -1);
						
						tvo = new TradeVO();
						tvo.setNo(Integer.parseInt(sno));
						tvo.setDate(sdf.format(new Date()));
						tvo.setType("출금");
						tvo.setAmount(Integer.parseInt(price));
						tdao.insert(tvo);
						
						System.out.printf("\n%s님의 계좌에서 %s 출금되었습니다.\n", vo.getName(), dfWon.format(Integer.parseInt(price)));
						
						int newBalance = vo.getBalance() - Integer.parseInt(price);
						balance = dfWon.format(newBalance);
						
						System.out.printf("현재 잔액은 %s 입니다.\n", balance);
					}
				}
				
				break;
				
			case "5":
				ArrayList<AccountVO> array = dao.list();
				for(AccountVO v : array) {
					balance = dfWon.format(v.getBalance());
					System.out.printf("계좌번호: %d\t계좌주명: %s\t잔액: %s\n", v.getNo(), v.getName(), balance);
				}
				System.out.printf("\n현재 %d개의 계좌가 등록되어 있습니다.\n", array.size());
				
				break;
				
			default:
				System.out.println("0~4번 중 선택해주세요.");
			}
		}
		
	}
}
