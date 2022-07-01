package fifthCommit;

import java.util.*;
import helloworld.Main;

public class FacilityManagement {
	public static void execute() {
		Scanner scanner = new Scanner(System.in);
		CampingDAO campingDAO = new CampingDAO();
		CampingVO campingVO = null;
		FacilityDAO facilityDAO = new FacilityDAO();
		
		boolean run = true;
		boolean isFind;
		int switchMenu;
		int code;
		int sel;
		ArrayList<String> farray;
		ArrayList<String> check;
		String[] facility = {"취소", "화장실", "전기", "수도", "와이파이", "놀이터", "수영장", "편의점"};
		
		while(run) {
			System.out.println("┌──────────────────────────────────────────┐");
			System.out.println("│ 1.시설물등록 │ 2.시설물삭제 │ 0.이전으로 돌아가기 │");
			System.out.println("└──────────────────────────────────────────┘");
			
			switchMenu = Main.input("메뉴를 선택해주세요.");
			
			switch(switchMenu) {
			case 0:
				run = false;
				
				break;
				
			case 1:
				code = Main.input("캠핑장 번호를 입력해주세요.");
				campingVO = campingDAO.read(code);
				if(campingVO.getName() == null) {
					System.out.println("해당 캠핑장이 없습니다.\n");
					break;
				}
				System.out.println("캠핑장 이름: " + campingVO.getName());
				System.out.println("캠핑장 주소: " + campingVO.getAddress());
				System.out.println("──────────────────────────────────────────────────────────────────────");
				farray = facilityDAO.list(code);
				check = new ArrayList<>();
				
				
				for(int i=0; i<facility.length; i++) {
					//시설물 유무 확인
					
					isFind = false;
					for(String f : farray) {
						if(facility[i].equals(f)) isFind = true;
					}
					if(isFind == true) {
						check.add("O");
					} else {
						check.add("X");
					}
					System.out.printf("[%d] %s \t[ %s ]\n", i, facility[i], check.get(i));
				}
				System.out.println("──────────────────────────────────────────────────────────────────────");
				sel = Main.input("시설물을 선택해주세요.");
				if(sel == 0) break;
				if(check.get(sel).equals("O")) {
					System.out.println("이미 등록되어 있는 시설물입니다.\n");
					break;
				}
				facilityDAO.insert(code, facility[sel]);
				System.out.println(facility[sel] + "이(가) 등록되었습니다.\n");
				
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
				farray = facilityDAO.list(code);
				check = new ArrayList<>();
				
				
				for(int i=0; i<facility.length; i++) {
					//시설물 유무 확인
					isFind = false;
					for(String f : farray) {
						if(facility[i].equals(f)) isFind = true;
					}
					if(isFind == true) {
						check.add("O");
					} else {
						check.add("X");
					}
					System.out.printf("[%d] %s \t[ %s ]\n", i, facility[i], check.get(i));
				}
				System.out.println("──────────────────────────────────────────────────────────────────────");
				sel = Main.input("시설물을 선택해주세요.");
				if(sel == 0) break;
				if(check.get(sel).equals("X")) {
					System.out.println("등록되지 않은 시설물입니다.\n");
					break;
				}
				facilityDAO.delete(code, facility[sel]);
				System.out.println(facility[sel] + "이(가) 삭제되었습니다.\n");
					
				break;
				
			default:
				System.out.println("0~2번 중 선택해주세요.");
				
			}
		}
	}
}
