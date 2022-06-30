package fourthCommit;

import java.util.*;
import java.io.*;

public class SaleDAO {
	File file = new File("C:/Data/java/ex13/sale.txt");
	
	//판매 등록
	public void insert(SaleVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			
			writer.write(vo.getNo() + "|" + vo.getDate() + "|" + vo.getQnt() + "\n");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("insert : " + e.toString());
		}
	}
	
	//특정 상품에 대한 판매 목록
	public ArrayList<SaleVO> list(int no) {
		ArrayList<SaleVO> array = new ArrayList<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int lineNo = Integer.parseInt(st.nextToken());
				
				if(no == lineNo) {
					SaleVO vo = new SaleVO();
					vo.setNo(lineNo);
					vo.setDate(st.nextToken());
					vo.setQnt(Integer.parseInt(st.nextToken()));
					
					array.add(vo);
				}
			}
		} catch (Exception e) {
			System.out.println(" list : " + e.toString());
		}
		
		return array;
	}
}
