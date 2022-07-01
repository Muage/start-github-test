package fifthCommit;

import java.io.*;
import java.util.*;

public class CampingDAO {
	File file = new File("C:/Data/java/helloworld/camping.txt");
	
	//마지막코드 가져오기
	public int getLastCode() {
		int code = 0;
		BufferedReader reader;
		String line;
		StringTokenizer st;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			line = "";
			
			while((line = reader.readLine()) != null) {
				st = new StringTokenizer(line, "|");
				code = Integer.parseInt(st.nextToken());
			}
			reader.close();
		} catch(Exception e) {
			System.out.println("마지막 코드를 확인해주세요. : " + e.toString());
		}
		return code;
	}
	//캠핑장등록
	public void insert(CampingVO campingVO) {
		try {
			FileWriter writer = new FileWriter(file, true);
			
			writer.write(campingVO.getCode() + "|" + campingVO.getName() + "|" + campingVO.getAddress() + "\n");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("캠핑장 등록을 확인해주세요. : " + e.toString());
		}
	}
	
	//캠핑장조회
	public CampingVO read(int code) {
		CampingVO campingVO = new CampingVO();
		BufferedReader reader;
		String line;
		StringTokenizer st;
		int lineCode;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			line = "";
			
			while((line = reader.readLine()) != null) {
				st = new StringTokenizer(line, "|");
				lineCode = Integer.parseInt(st.nextToken());
				
				if(code == lineCode) {
					campingVO.setCode(lineCode);
					campingVO.setName(st.nextToken());
					campingVO.setAddress(st.nextToken());
				}
			}
		} catch(Exception e) {
			System.out.println("캠핑장 조회를 확인해주세요. : " + e.toString());
		}
		return campingVO;
	}
	
	//캠핑장목록
	public ArrayList<CampingVO> list(){
		ArrayList<CampingVO> array = new ArrayList<>();
		BufferedReader reader;
		String line;
		StringTokenizer st;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			line = "";
			
			while((line = reader.readLine()) != null) {
				st = new StringTokenizer(line, "|");
				CampingVO campingVO = new CampingVO();
				
				campingVO.setCode(Integer.parseInt(st.nextToken()));
				campingVO.setName(st.nextToken());
				campingVO.setAddress(st.nextToken());
				
				array.add(campingVO);
			}
		} catch(Exception e) {
			System.out.println("캠핑장 목록을 확인해주세요. : " + e.toString());
		}
		return array;
	}
}
