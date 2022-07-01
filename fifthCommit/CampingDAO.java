package fifthCommit;

import java.io.*;
import java.util.*;

public class CampingDAO {
	File file = new File("C:/Data/java/helloworld/camping.txt");
	
	//�������ڵ� ��������
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
			System.out.println("������ �ڵ带 Ȯ�����ּ���. : " + e.toString());
		}
		return code;
	}
	//ķ������
	public void insert(CampingVO campingVO) {
		try {
			FileWriter writer = new FileWriter(file, true);
			
			writer.write(campingVO.getCode() + "|" + campingVO.getName() + "|" + campingVO.getAddress() + "\n");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("ķ���� ����� Ȯ�����ּ���. : " + e.toString());
		}
	}
	
	//ķ������ȸ
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
			System.out.println("ķ���� ��ȸ�� Ȯ�����ּ���. : " + e.toString());
		}
		return campingVO;
	}
	
	//ķ������
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
			System.out.println("ķ���� ����� Ȯ�����ּ���. : " + e.toString());
		}
		return array;
	}
}
