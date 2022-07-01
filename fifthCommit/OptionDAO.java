package fifthCommit;

import java.io.*;
import java.util.*;

public class OptionDAO {
	File file = new File("C:/Data/java/helloworld/option.txt");
	
	//Ư�� ķ������ �ɼ� ���
	public ArrayList<String> list(int code) {
		ArrayList<String> array = new ArrayList<>();
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
					array.add(st.nextToken());
				}
			}
		} catch(Exception e) {
			System.out.println("�ɼ� ����� Ȯ�����ּ���. : " + e.toString());
		}
		
		return array;
	}
	
	//�ɼ� ���
		public void insert(int code, String option) {
			try {
				FileWriter writer = new FileWriter(file, true);
				
				writer.write(code + "|" + option + "\n");
				writer.flush();
				writer.close();
			} catch(Exception e) {
				System.out.println("�ɼ� ����� Ȯ�����ּ���. : " + e.toString());
			}
		}
		
		//�ü��� ����
		public void delete(int code, String option) {
			BufferedReader reader;
			FileWriter writer;
			String line;
			String lines;
			String lineFacil;
			StringTokenizer st;
			int lineCode;
			
			try {
				reader = new BufferedReader(new FileReader(file));
				line = "";
				lines = "";
				
				while((line = reader.readLine()) != null) {
					st = new StringTokenizer(line, "|");
					lineCode = Integer.parseInt(st.nextToken());
					lineFacil = st.nextToken();
					if(lineCode != code || !lineFacil.equals(option)) {
						lines = lines + line + "\n";
					}
				}
				writer = new FileWriter(file, false);
				
				writer.write(lines);
				writer.flush();
				writer.close();
			} catch(Exception e) {
				System.out.println("�ɼ� ������ Ȯ�����ּ���. : " + e.toString());
			}
		}
}
