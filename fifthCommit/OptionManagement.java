package fifthCommit;

import java.util.*;
import helloworld.Main;

public class OptionManagement {
	public static void execute() {
		Scanner scanner = new Scanner(System.in);
		CampingDAO campingDAO = new CampingDAO();
		CampingVO campingVO = null;
		OptionDAO optionDAO = new OptionDAO();
				
		boolean run = true;
		boolean isFind;
		int switchMenu;
		int code;
		int sel;
		ArrayList<String> tarray;
		ArrayList<String> check;
		String[] option = {"취소", "오토캠핑", "카라반", "글램핑", "펜션"};
		
		while(run) {
			System.out.println("┌────────────────────────────────────────────────┐");
			System.out.println("│ 1.시설물종류등록 │ 2.시설물종류삭제 │ 0.이전으로 돌아가기 │");
			System.out.println("└────────────────────────────────────────────────┘");
			
			switchMenu = Main.input("메뉴를 선택해주세요.");
			
			switch(switchMenu) {
			case 0:
				run = false;
				
				break;
				
			case 1:
				code = Main.input("캠핑장 번호를 입력해주세요.");
				campingVO = campingDAO.read(code);
				if(campingVO.getName() == null) {
					System.out.println("입력하신 번호로 등록된 캠핑장이 없습니다.\n");
					break;
				}
				System.out.println("캠핑장 이름 : " + campingVO.getName());
				System.out.println("캠핑장 주소: " + campingVO.getAddress());
				System.out.println("──────────────────────────────────────────────────────────────────────");
				tarray = optionDAO.list(code);
				check = new ArrayList<>();
				
				for(int i=0; i<option.length; i++) {
					
					isFind = false;
					for(String t : tarray) {
						if(t.equals(option[i])) isFind = true;
					}
					if(isFind == true) {
							check.add("O");
					} else {
						check.add("X");
					}
					System.out.printf("[%d] %s \t[ %s ]\n", i, option[i], check.get(i));
				}
				System.out.println("──────────────────────────────────────────────────────────────────────");
				sel = Main.input("유형을 선택해주세요.");
				if(sel == 0) break;
				if(sel == 0) break;
				if(check.get(sel).equals("O")) {
					System.out.println("이미 등록되어 있는 유형입니다.\n");
					break;
				}
				//종류 등록
				optionDAO.insert(code, option[sel]);
				System.out.println(option[sel] + " 유형이 등록되었습니다.\n");
				
				break;
				
			case 2:
				code = Main.input("캠핑장 번호를 입력해주세요.");
				campingVO = campingDAO.read(code);
				if(campingVO.getName() == null) {
					System.out.println("해당 캠핑장이 없습니다.\n");
					break;
				}
				System.out.println("캠핑장 이름: " + campingVO.getName());
				System.out.println("캠핑장 주소: " + campingVO.getAddress());
				System.out.println("──────────────────────────────────────────────────────────────────────");
				tarray = optionDAO.list(code);
				check = new ArrayList<>();
				
				
				for(int i=0; i<option.length; i++) {
					//시설물 유무 확인
					
					isFind = false;
					for(String t : tarray) {
						if(option[i].equals(t)) isFind = true;
					}
					if(isFind == true) {
						check.add("O");
					} else {
						check.add("X");
					}
					System.out.printf("[%d] %s \t[ %s ]\n", i, option[i], check.get(i));
				}
				System.out.println("──────────────────────────────────────────────────────────────────────");
				sel = Main.input("유형을 선택해주세요.");
				if(sel == 0) break;
				if(check.get(sel).equals("X")) {
					System.out.println("등록되지 않은 유형입니다.\n");
					break;
				}
				optionDAO.delete(code, option[sel]);
				System.out.println(option[sel] + "유형이 삭제되었습니다.\n");
				
				break;
			
			default:
				System.out.println("0~2번 중 선택해주세요.\n");
			}
		}
	}
}
