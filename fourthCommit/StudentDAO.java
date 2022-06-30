package fourthCommit;

import java.io.*;
import java.util.*;

public class StudentDAO {
	File file = new File("C:/Data/java/ex13/student.txt");
	StudentVO vo  = new StudentVO();
	ArrayList<StudentVO> array = new ArrayList<>();
	
	//학생 조회
	public StudentVO read(int no) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int lineNo = Integer.parseInt(st.nextToken());
				if(no == lineNo) {
					vo.setNo(lineNo);
					vo.setName(st.nextToken());
					vo.setAddress(st.nextToken());
					
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("학생 조회를 확인해주세요." + e.toString());
		}
		
		return vo;
	}
	
	//학생 입력
	public void insert(StudentVO vo) {
		try{
			FileWriter writer = new FileWriter(file, true);
			
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getAddress() + "\n");
			writer.flush();	//버퍼 내용을 지워줌
			writer.close();
			
		} catch(Exception e) {
			System.out.println("학생 입력을 확인해주세요." + e.toString());
		}
		
	}
	
	//마지막 학번 가져오기
	public int getLastNo() {
		int no = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				no = Integer.parseInt(st.nextToken());
			}
		} catch(Exception e) {
			System.out.println("마지막 번호를 확인해주세요." + e.toString());
		}
		
		return no;
	}
	
	//학생 목록
	public ArrayList<StudentVO> list(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				StudentVO vo = new StudentVO();
				
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				vo.setAddress(st.nextToken());
				
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("학생 목록을 확인해주세요." + e.toString());
		}
		
		return array;
	}
	
}
