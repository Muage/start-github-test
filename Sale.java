package secondCommit;

import java.util.*;

public class Sale {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		SaleDAO dao = new SaleDAO();
		SaleVO vo = null;
		boolean run = true;
		
		while(run) {
			System.out.println("\n***************** 매 출 관 리 *****************");
			System.out.println("---------------------------------------------");
			System.out.println("1.목록 | 2.등록 | 3.수정 | 4.검색 | 5.삭제 | 0.종료");
			System.out.println("---------------------------------------------");
			System.out.print("원하시는 업무를 선택해주세요. > ");
			int menu = sc.nextInt(); sc.nextLine();
			
			switch(menu) {
			case 0:
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				
				break;
				
			case 1:
				for(SaleVO v : dao.list()) {
					System.out.println(v.toString());
				}
				
				break;
				
			case 2:
				int newNo = dao.getLastNo() + 100;
				System.out.println("상품 번호 : " + newNo);
				vo = new SaleVO();
				vo.setNo(newNo);
				
				System.out.print("상품명을 입력해주세요. : ");
				vo.setName(sc.nextLine());
				
				System.out.print("상품 단가를 입력해주세요. : ");
				vo.setPrice(sc.nextInt()); sc.nextLine();
				
				System.out.print("판매 수량을 입력해주세요. : ");
				vo.setQnt(sc.nextInt()); sc.nextLine();
				
				dao.insert(vo);
				System.out.printf("%d번 상품이 등록되었습니다.\n", newNo);
				
				break;
				
			case 3:
				String no = "";
				boolean isNumber = false;
				do {
					System.out.print("수정할 번호를 입력해주세요. : ");
					no = sc.nextLine();
					isNumber = no.matches("-?\\d+(\\.\\d+)?");
					if(!isNumber) System.out.println("숫자로 입력하세요.");
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(no));
				
				if(vo.getName() == null) {
					System.out.println("입력하신 번호로 등록된 상품이 없습니다.");
				} else {
					System.out.print("상품명 : " + vo.getName() + " > ");
					String name = sc.nextLine();
					
					System.out.print("상품단가 : " + vo.getPrice() + " > ");
					String price = sc.nextLine();
					
					System.out.print("판매수량 : " + vo.getQnt() + " > ");
					String qnt = sc.nextLine();
					
					System.out.printf("상품번호 %s번의 정보를 수정하시겠습니까? ( 예 : Y , 아니요 : N) > ", no);
					String sel = sc.nextLine();
					
					if (sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
						if(!name.equals("")) vo.setName(name);
						if(!price.equals("")) vo.setPrice(Integer.parseInt(price));
						if(!qnt.equals("")) vo.setQnt(Integer.parseInt(qnt));
						
						dao.update(vo);
						System.out.printf("상품번호 %s번의 정보가 수정되었습니다.\n", no);
						System.out.println(vo.toString());
					}
				}
				
				break;
				
			case 4:
				no = "";
				isNumber = false;
				do {
					System.out.print("검색할 번호를 입력해주세요. : ");
					no = sc.nextLine();
					isNumber = no.matches("-?\\d+(\\.\\d+)?");
					if(!isNumber) System.out.println("숫자로 입력하세요.");
				} while(isNumber == false);
				
				vo = dao.read(Integer.parseInt(no));
				
				if(vo.getName() == null) {
					System.out.println("입력하신 번호로 등록된 상품이 없습니다.");
				} else {
					System.out.println(vo.toString());
				}
				
				break;
				
			case 5:
				System.out.print("삭제할 번호를 입력해주세요. : ");
				int dno = sc.nextInt(); sc.nextLine();
				
				vo = dao.read(dno);
				
				if(vo.getName() == null) {
					System.out.println("입력하신 번호로 등록된 상품이 없습니다.");
				} else {
					dao.delete(dno);
					System.out.printf("%d번 %s 상품이 삭제되었습니다.\n", dno, vo.getName());
				}
				
				break;
				
			default:
				System.out.println("0~5번 중 선택해주세요.");
			}
		}
	}
}
