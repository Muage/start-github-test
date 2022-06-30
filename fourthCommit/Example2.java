package fourthCommit;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Example2 {
	public static void execute() {
		Scanner scanner = new Scanner(System.in);
		DecimalFormat dfWon = new DecimalFormat("#,###만원");
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		
		ProductVO productVO = null;
		ProductDAO productDAO = new ProductDAO();
		SaleVO saleVO = null;
		SaleDAO saleDAO = new SaleDAO();
		boolean run = true;
		
		while(run) {
			System.out.println("\n\t\t    [상품관리 프로그램]");
			System.out.println("┌──────────┬──────────┬──────────┬──────────┬───────┐");
			System.out.println("│ 1.상품등록 │ 2.상품조회 │ 3.상품목록 │ 4.매출등록 │ 0.종료 │");
			System.out.println("└──────────┴──────────┴──────────┴──────────┴───────┘");
			
			System.out.print("원하시는 업무를 선택해주세요. > ");
			String menu = scanner.nextLine();
			switch(menu) {
			case "0":
				run = false;
				System.out.println("프로그램을 종료합니다.");
				
				break;
				
			case "1":
				int newCode = productDAO.getLastNo() + 100;
				System.out.println("상품번호: " + newCode);
				productVO = new ProductVO();
				productVO.setNo(newCode);
				
				System.out.print("상품 이름을 입력해주세요. : ");
				productVO.setName(scanner.nextLine());
				
				System.out.print("상품 단가를 입력해주세요. : ");
				productVO.setPrice(scanner.nextInt()); scanner.nextLine();
				
				System.out.print("재고 수량을 입력해주세요. : ");
				productVO.setQnt(scanner.nextInt()); scanner.nextLine();
				
				productDAO.insert(productVO);
				System.out.printf("%s 상품이 등록되었습니다.\n", productVO.getName());
				
				break;
				
			case "2":
				String sno = "";
				boolean isNumber = false;
				do {
					System.out.print("조회할 상품번호를 입력해주세요. : ");
					sno = scanner.nextLine();
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					if(!isNumber) System.out.println("상품 조회할 번호를 숫자로 입력해주세요.\n");
				} while(isNumber == false);
					
				productVO = productDAO.read(Integer.parseInt(sno));
				System.out.println("");
				
				if(productVO.getName() == null) {
					System.out.println("입력하신 번호로 등록된 상품이 없습니다.\n");
				} else {
					System.out.println(productVO.toString());
					//상품 판매 목록
					ArrayList<SaleVO> sarray = saleDAO.list(Integer.parseInt(sno));
					if(sarray.size() == 0) {
						System.out.println("");
						System.out.println("판매한 상품이 없습니다.");
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
				System.out.println("┌───────┬───────┬───────┬───────┬───────────┐");
				System.out.println("│상품번호\t│상품이름\t│상품단가\t│재고수량\t│\t금액 │");
				System.out.println("├───────┼───────┼───────┼───────┼───────────┤");
				for(ProductVO v : array) {
					int sum = v.getPrice() * v.getQnt();
					String strSum = dfWon.format(sum);
					String price = dfWon.format(v.getPrice());
					
					System.out.printf("│%d\t│%s\t│%s\t│%d개\t│%10s│\n", v.getNo(), v.getName(), price, v.getQnt(), strSum);
				}
				System.out.println("└───────┴───────┴───────┴───────┴───────────┘");

				System.out.printf("현재 등록되어 있는 상품은 총 %d개 입니다.\n", array.size());
				
				break;
				
			case "4":
				sno = "";
				isNumber = false;
				do {
					System.out.print("매출을 등록할 상품번호를 입력해주세요. : ");
					sno = scanner.nextLine();
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					if(!isNumber) System.out.println("매출을 등록할 번호를 숫자로 입력해주세요.\n");
				} while(isNumber == false);
					
				productVO = productDAO.read(Integer.parseInt(sno));
				System.out.println("");
				
				if(productVO.getName() == null) {
					System.out.println("입력하신 번호로 등록된 상품이 없습니다.\n");
				} else {
					System.out.println(productVO.toString());
					
					//상품 판매 목록
					ArrayList<SaleVO> sarray = saleDAO.list(Integer.parseInt(sno));
					if(sarray.size() == 0) {
						System.out.println("");
						System.out.println("판매한 상품이 없습니다.");
						System.out.printf("현재 재고 수량은 %d개 입니다. \n", productVO.getQnt());
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
					System.out.println("판매일자 : " + saleVO.getDate());

					System.out.print("판매수량을 입력해주세요. : ");
					saleVO.setQnt(scanner.nextInt()); scanner.nextLine();
					saleDAO.insert(saleVO);
					
					//재고수량 - 판매수량
					productDAO.update(Integer.parseInt(sno), saleVO.getQnt());
					
					System.out.printf("\n%s 상품의 매출이 등록되었습니다.\n", productVO.getName());
					break;
				}
				
				break;
				
			default:
				System.out.println("0~4번 중 선택해주세요.");
			}
		}
	}
}
