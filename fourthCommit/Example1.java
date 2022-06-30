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
			System.out.println("\n[성적관리 프로그램]");
			System.out.println("┌──────────┬──────────┬──────────┬──────────┬───────┐");
			System.out.println("│ 1.학생등록 │ 2.학생조회 │ 3.학생목록 │ 4.성적등록 │ 0.종료 │");
			System.out.println("└──────────┴──────────┴──────────┴──────────┴───────┘");
			
			System.out.print("원하시는 업무를 선택해주세요. > ");
			String menu = scanner.nextLine();
			switch(menu) {
			case "0":
				run = false;
				System.out.println("프로그램을 종료합니다.");
				
				break;
				
			case "1":
				int newNo = dao.getLastNo() + 100;
				System.out.println("학번: " + newNo);
				vo = new StudentVO();
				vo.setNo(newNo);
				
				System.out.print("이름을 입력해주세요. : ");
				vo.setName(scanner.nextLine());
				
				System.out.print("주소를 입력해주세요. : ");
				vo.setAddress(scanner.nextLine());
				
				dao.insert(vo);
				System.out.printf("\n%d번 %s 학생이 등록되었습니다.\n", newNo, vo.getName());
				
				break;
				
			case "2":
				String sno = "";
				boolean isNumber = false;
				do {
					System.out.print("성적을 조회할 학번을 입력해주세요. : ");
					sno = scanner.nextLine();
					isNumber = sno.matches("-?\\d+(\\.\\d+)?");
					if(!isNumber) System.out.println("성적 조회할 학번을 숫자로 입력해주세요.\n");
				} while(isNumber == false);
				vo = dao.read(Integer.parseInt(sno));
				if(vo.getName() == null) {
					System.out.println("입력하신 학번으로 등록된 학생이 없습니다.");
				} else {
					//학생 정보 출력
					System.out.printf("이름: %s(%s)\n", vo.getName(), vo.getAddress());
					//성적 목록 출력
					System.out.println("┌───────┬───────┬───────┬───────┬───────┬─────┐");
					System.out.println("│구분\t│국어\t│영어\t│수학\t│총점\t│평균  │");
					System.out.println("├───────┼───────┼───────┼───────┼───────┼─────┤");
					ArrayList<ScoreVO> tarray = scoreDAO.list(Integer.parseInt(sno));
					for(ScoreVO v : tarray) {
						v.print();
					}
					System.out.println("└───────┴───────┴───────┴───────┴───────┴─────┘");
				}
					
				break;
				
			case "3":
				System.out.println("");
				
				ArrayList<StudentVO> array = dao.list();
				for(StudentVO v : array) {
					System.out.printf("학번: %s\t이름: %s\t주소: %s\n", v.getNo(), v.getName(), v.getAddress());
				}
					System.out.println("─────────────────────────────────────────────────────");
					System.out.printf("현재 등록된 학생은 총 %d명 입니다.\n", array.size());
				
				break;
				
			case "4":
				String no = "";
				isNumber = false;
				do {
					System.out.print("성적을 등록할 학번을 입력해주세요. : ");
					no = scanner.nextLine();
					isNumber = no.matches("-?\\d+(\\.\\d+)?");
					
					if(!isNumber) System.out.println("성적 등록할 학번을 숫자로 입력해주세요.\n");
				} while(isNumber == false);
				
				//학생 정보 읽기
				vo = dao.read(Integer.parseInt(no));
				if(vo.getName() == null) {
					System.out.println("입력하신 학번으로 등록된 학생이 없습니다.");
				} else {
					//학생 정보 출력
					System.out.printf("이름: %s(%s)\n", vo.getName(), vo.getAddress());
					
					//성적 목록 출력
					scoreVO = scoreDAO.read(Integer.parseInt(no));
					if(scoreVO.getType() == null) {
						System.out.printf("%s 학생의 등록된 성적이 없습니다.\n", vo.getName());
						System.out.printf("%s 학생의 성적을 등록하시겠습니까? ( 예 : Y , 아니요 : N) > ", vo.getName());
						String sel = scanner.nextLine();
						
						String type = "";
						String kor = "";
						String eng = "";
						String mat = "";
						if (sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
							System.out.println("");
							System.out.print("구분(중간/기말/모의)을 입력해주세요. : ");
							type = scanner.nextLine();
							
							isNumber = false;
							do {
								System.out.print("국어 점수를 입력해주세요. : ");
								kor = scanner.nextLine();
								isNumber = kor.matches("-?\\d+(\\.\\d+)?");
								
								if(!isNumber) System.out.println("국어 점수를 숫자로 입력해주세요.\n");
							} while(isNumber == false);
							
							isNumber = false;
							do {
								System.out.print("영어 점수를 입력해주세요. : ");
								eng = scanner.nextLine();
								isNumber = eng.matches("-?\\d+(\\.\\d+)?");
								
								if(!isNumber) System.out.println("영어 점수를 숫자로 입력해주세요.\n");
							} while(isNumber == false);
							
							isNumber = false;
							do {
								System.out.print("수학 점수를 입력해주세요. : ");
								mat = scanner.nextLine();
								isNumber = mat.matches("-?\\d+(\\.\\d+)?");
								
								if(!isNumber) System.out.println("수학 점수를 숫자로 입력해주세요.\n");
							} while(isNumber == false);
							
							scoreVO = new ScoreVO();
							scoreVO.setNo(Integer.parseInt(no));
							scoreVO.setType(type);
							scoreVO.setKor(Integer.parseInt(kor));
							scoreVO.setEng(Integer.parseInt(eng));
							scoreVO.setMat(Integer.parseInt(mat));
							
							scoreDAO.insert(scoreVO);
							System.out.printf("%s 학생의 %s 성적이 등록되었습니다.\n", vo.getName() , type);
							
							break;
						}
					}else {
						System.out.println("┌───────┬───────┬───────┬───────┬───────┬─────┐");
						System.out.println("│구분\t│국어\t│영어\t│수학\t│총점\t│평균  │");
						System.out.println("├───────┼───────┼───────┼───────┼───────┼─────┤");
						ArrayList<ScoreVO> tarray = scoreDAO.list(Integer.parseInt(no));
						for(ScoreVO v : tarray) {
							v.print();
						}
						System.out.println("└───────┴───────┴───────┴───────┴───────┴─────┘");
					}
				
					//구분 입력
					System.out.print("구분(중간/기말/모의)을 입력해주세요. : ");
					String type = scanner.nextLine();
					
					//국어 점수
					String kor = "";
					isNumber = false;
					do {
						System.out.print("국어 점수를 입력해주세요. : ");
						kor = scanner.nextLine();
						isNumber = kor.matches("-?\\d+(\\.\\d+)?");
						
						if(!isNumber) System.out.println("국어 점수를 숫자로 입력해주세요.\n");
					} while(isNumber == false);
					
					//영어 점수
					String eng = "";
					isNumber = false;
					do {
						System.out.print("영어 점수를 입력해주세요. : ");
						eng = scanner.nextLine();
						isNumber = eng.matches("-?\\d+(\\.\\d+)?");
						
						if(!isNumber) System.out.println("영어 점수를 숫자로 입력해주세요.\n");
					} while(isNumber == false);
					
					//수학 점수
					String mat = "";
					isNumber = false;
					do {
						System.out.print("수학 점수를 입력해주세요. : ");
						mat = scanner.nextLine();
						isNumber = mat.matches("-?\\d+(\\.\\d+)?");
						
						if(!isNumber) System.out.println("수학 점수를 숫자로 입력해주세요.\n");
					} while(isNumber == false);
					
					scoreVO = new ScoreVO();
					scoreVO.setNo(Integer.parseInt(no));
					scoreVO.setType(type);
					scoreVO.setKor(Integer.parseInt(kor));
					scoreVO.setEng(Integer.parseInt(eng));
					scoreVO.setMat(Integer.parseInt(mat));
					
					scoreDAO.insert(scoreVO);
					System.out.printf("%s 학생의 %s 성적이 등록되었습니다.\n", vo.getName() , type);
				}
				
//				System.out.printf("%s 학생의 %s 성적이 등록되었습니다.", , type);
				
				break;
				
			default:
				System.out.println("0~4번 중 선택해주세요.");
			}
		}
	}
}
