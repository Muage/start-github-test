package thirdCommit;

import java.io.*;
import java.util.*;

public class AccountDAO {
	File file = new File("C:/Data/java/ex12/account.txt");
	
	public void trade(int no, int price) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			String lines = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				AccountVO vo = new AccountVO();
				
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				int balance = Integer.parseInt(st.nextToken());
				vo.setBalance(balance + price);
				
				if(no == vo.getNo()) {
					lines = lines + vo.getNo() + "|" + vo.getName() + "|" + vo.getBalance() + "\n";
				} else {
					lines = lines + line + "\n";
				}
			}
			FileWriter writer = new FileWriter(file, false);
			
			writer.write(lines);
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("입출금을 확인해주세요." + e.toString());
		}
	}
	
	public ArrayList<AccountVO> list(){
		ArrayList<AccountVO> array = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				AccountVO vo = new AccountVO();
				
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				vo.setBalance(Integer.parseInt(st.nextToken()));
				
				array.add(vo);
			}
			
		} catch(Exception e) {
			System.out.println("목록을 확인해주세요." + e.toString());
		}
		return array;
	}
	
	public void insert(AccountVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getBalance() + "\n");
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("계좌생성을 확인해주세요." + e.toString());
		}
	}
	//마지막 계좌번호 가지고오기
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
			System.out.println("마지막 계좌번호 값을 확인해주세요." + e.toString());
		}
		return no;
	}
	
	public void update(AccountVO vo) {
		
	}
	
	public AccountVO read(int no) {
		AccountVO vo = new AccountVO();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				
				vo.setNo(Integer.parseInt(st.nextToken()));
				
				if(no == vo.getNo()) {
					vo.setName(st.nextToken());
					vo.setBalance(Integer.parseInt(st.nextToken()));
					
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("조회를 확인해주세요." + e.toString());
		}
		return vo;
	}
	
	public void delete(int no) {
		
	}
}
