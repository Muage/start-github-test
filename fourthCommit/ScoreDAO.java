package fourthCommit;

import java.io.*;
import java.util.*;

public class ScoreDAO {
	File file = new File("C:/Data/java/ex13/score.txt");
	
	//���� ��ȸ
	public ScoreVO read(int no) {
		ScoreVO vo  = new ScoreVO();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int lineNo = Integer.parseInt(st.nextToken());
				
				if(no == lineNo) {
					vo.setNo(lineNo);
					vo.setType(st.nextToken());
					vo.setKor(Integer.parseInt(st.nextToken()));
					vo.setEng(Integer.parseInt(st.nextToken()));
					vo.setMat(Integer.parseInt(st.nextToken()));
					
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("�л� ��ȸ�� Ȯ�����ּ���." + e.toString());
		}
		
		return vo;
	}
	
	//���� ���
	public void insert(ScoreVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			
			writer.write(vo.getNo() + "|" + vo.getType() + "|" + vo.getKor() + "|" + vo.getEng() + "|" + vo.getMat() + "\n");
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("insert : " + e.toString());
		}
	}
	
	//Ư�� �й��� ���� ���
	public ArrayList<ScoreVO> list(int no) {
		ArrayList<ScoreVO> array = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				int lineNo = Integer.parseInt(st.nextToken());
				
				if(no == lineNo) {
					ScoreVO vo = new ScoreVO();
					vo.setNo(lineNo);
					vo.setType(st.nextToken());
					vo.setKor(Integer.parseInt(st.nextToken()));
					vo.setEng(Integer.parseInt(st.nextToken()));
					vo.setMat(Integer.parseInt(st.nextToken()));
					array.add(vo);
				}
			}
		} catch(Exception e) {
			System.out.println("list : " + e.toString());
		}
		
		return array;
		
		
	}
}
