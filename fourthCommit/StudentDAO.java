package fourthCommit;

import java.io.*;
import java.util.*;

public class StudentDAO {
	File file = new File("C:/Data/java/ex13/student.txt");
	StudentVO vo  = new StudentVO();
	ArrayList<StudentVO> array = new ArrayList<>();
	
	//�л� ��ȸ
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
			System.out.println("�л� ��ȸ�� Ȯ�����ּ���." + e.toString());
		}
		
		return vo;
	}
	
	//�л� �Է�
	public void insert(StudentVO vo) {
		try{
			FileWriter writer = new FileWriter(file, true);
			
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getAddress() + "\n");
			writer.flush();	//���� ������ ������
			writer.close();
			
		} catch(Exception e) {
			System.out.println("�л� �Է��� Ȯ�����ּ���." + e.toString());
		}
		
	}
	
	//������ �й� ��������
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
			System.out.println("������ ��ȣ�� Ȯ�����ּ���." + e.toString());
		}
		
		return no;
	}
	
	//�л� ���
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
			System.out.println("�л� ����� Ȯ�����ּ���." + e.toString());
		}
		
		return array;
	}
	
}
