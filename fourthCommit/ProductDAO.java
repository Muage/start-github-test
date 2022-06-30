package fourthCommit;

import java.util.*;
import java.io.*;

public class ProductDAO {
	File file = new File("C:/Data/java/ex13/product.txt");
	
	//������ - �Ǹż���
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
			//���ο� ���� ����
			FileWriter writer = new FileWriter(file, false);
			
			writer.write(lines);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("������ ������ Ȯ�����ּ���." + e.toString());
		}
	}
	
	//��ǰ ��ȸ
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
			System.out.println("��ǰ ��ȸ�� Ȯ�����ּ���." + e.toString());
		}
		
		return vo;
	}
	
	//��ǰ ���
	public void insert(ProductVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getPrice() + "|" + vo.getQnt());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("��ǰ ����� Ȯ�����ּ���." + e.toString());
		}
	}
	
	//������ ��ǰ �ڵ� ��������
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
			System.out.println("������ ��ǰ �ڵ带 Ȯ�����ּ���." + e.toString());
		}
		
		return no;
	}
	
	//��ǰ ���
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
			System.out.println("��ǰ ����� Ȯ�����ּ���." + e.toString());
		}
		return array;
	}
}
