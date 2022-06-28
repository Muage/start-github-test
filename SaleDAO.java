package secondCommit;

import java.io.*;
import java.util.*;

public class SaleDAO {
	File file = new File("C:/Data/java/helloworld/sales.txt");
	
	public ArrayList<SaleVO> list(){
		ArrayList<SaleVO> array = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				SaleVO vo = new SaleVO();
				
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				vo.setPrice(Integer.parseInt(st.nextToken()));
				vo.setQnt(Integer.parseInt(st.nextToken()));
				
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("목록을 확인해주세요.");
			System.out.println(e.toString());
		}
		return array;
	}
	
	public void insert(SaleVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getPrice() + "|" + vo.getQnt() + "\n");
			writer.flush();
			writer.close();
			
		} catch(Exception e) {
			System.out.println("등록을 확인해주세요.");
			System.out.println(e.toString());
		}
	}
	
	public void update(SaleVO vo) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			String lines = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int no = Integer.parseInt(st.nextToken());
				
				if(no == vo.getNo()) {
					lines = lines + vo.getNo() + "|" + vo.getName() + "|" + vo.getPrice() + "|" + vo.getQnt() + "\n";
				} else {
					lines = lines + line + "\n";
				}
			}
			FileWriter writer = new FileWriter(file, false);
			
			writer.write(lines);
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("수정을 확인해주세요.");
			System.out.println(e.toString());
		}
	}
	
	public SaleVO read(int no) {
		SaleVO vo = new SaleVO();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				vo.setNo(Integer.parseInt(st.nextToken()));
				
				if(no == vo.getNo()) {
					vo.setName(st.nextToken());
					vo.setPrice(Integer.parseInt(st.nextToken()));
					vo.setQnt(Integer.parseInt(st.nextToken()));
					
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("검색을 확인해주세요.");
			System.out.println(e.toString());
		}
		return vo;
	}
	
	public void delete(int no) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			String lines = "";
			
			while ((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				SaleVO vo = new SaleVO();
				
				vo.setNo(Integer.parseInt(st.nextToken()));
				if(no != vo.getNo()) {
					lines = lines + line + "\n";
				}
			}
			FileWriter writer = new FileWriter(file, false);
			
			writer.write(lines);
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("삭제를 확인해주세요.");
			System.out.println(e.toString());
		}
	}
	
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
			System.out.println("getLastNo를 확인해주세요.");
			System.out.println(e.toString());
		}
		return no;
	}
}
