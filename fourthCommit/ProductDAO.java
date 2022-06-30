package fourthCommit;

import java.util.*;
import java.io.*;

public class ProductDAO {
	File file = new File("C:/Data/java/ex13/product.txt");
	
	//재고수량 - 판매수량
	public void update(int no, int qnt) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			String lines = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int lineNo = Integer.parseInt(st.nextToken());
				
				ProductVO vo = new ProductVO();
				vo.setNo(lineNo);
				vo.setName(st.nextToken());
				vo.setPrice(Integer.parseInt(st.nextToken()));
				vo.setQnt(Integer.parseInt(st.nextToken()));
				
				vo.setQnt(vo.getQnt() - qnt);
				
				if(no == lineNo) {
					lines = lines + vo.getNo() + "|" + vo.getName() + "|" + vo.getPrice() + "|" + vo.getQnt() + "\n";
				} else {
					lines = lines + line + "\n";
				}
			}
			//새로운 파일 저장
			FileWriter writer = new FileWriter(file, false);
			
			writer.write(lines);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("재고수량 계산식을 확인해주세요." + e.toString());
		}
	}
	
	//상품 조회
	public ProductVO read(int no) {
		ProductVO vo = new ProductVO();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int lineNo = Integer.parseInt(st.nextToken());
				
				if(no == lineNo) {
					vo.setNo(lineNo);
					vo.setName(st.nextToken());
					vo.setPrice(Integer.parseInt(st.nextToken()));
					vo.setQnt(Integer.parseInt(st.nextToken()));
					
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("상품 조회를 확인해주세요." + e.toString());
		}
		
		return vo;
	}
	
	//상품 등록
	public void insert(ProductVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getPrice() + "|" + vo.getQnt());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("상품 등록을 확인해주세요." + e.toString());
		}
	}
	
	//마지막 상품 코드 가져오기
	public int getLastNo() {
		int no = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				no = Integer.parseInt(st.nextToken());
			}
		} catch (Exception e) {
			System.out.println("마지막 상품 코드를 확인해주세요." + e.toString());
		}
		
		return no;
	}
	
	//상품 목록
	public ArrayList<ProductVO> list() {
		ArrayList<ProductVO> array = new ArrayList<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				ProductVO vo = new ProductVO();
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				vo.setPrice(Integer.parseInt(st.nextToken()));
				vo.setQnt(Integer.parseInt(st.nextToken()));
				
				array.add(vo);
			}
		} catch (Exception e) {
			System.out.println("상품 목록을 확인해주세요." + e.toString());
		}
		return array;
	}
}
