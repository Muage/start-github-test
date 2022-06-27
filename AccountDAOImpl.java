package helloworld;

import java.util.ArrayList;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public ArrayList<AccountVO> list() {
		ArrayList<AccountVO> array = new ArrayList<>();
		
		AccountVO vo = new AccountVO();
		vo.setNo(111221000);
		vo.setName("홍길동");
		vo.setBalance(10000);
		array.add(vo);
		
		vo = new AccountVO();
		vo.setNo(111332000);
		vo.setName("심청이");
		vo.setBalance(20000);
		array.add(vo);
		
		vo = new AccountVO();
		vo.setNo(111443000);
		vo.setName("강감찬");
		vo.setBalance(30000);
		array.add(vo);
		
		return array;
	}

	@Override
	public void insert(AccountVO vo, ArrayList<AccountVO> array) {
		array.add(vo);
	}

	@Override
	public AccountVO read(int no, ArrayList<AccountVO> array) {
		AccountVO vo = new AccountVO();
		
		for(AccountVO v : array) {
			if(no == v.getNo()) {
				vo.setNo(v.getNo());
				vo.setName(v.getName());
				vo.setBalance(v.getBalance());
			}
		}
		
		return vo;
	}

	@Override
	public void trade(int no, int amount, ArrayList<AccountVO> array) {
		for(AccountVO v : array) {
			if(no == v.getNo()) {
				v.setBalance(v.getBalance() + amount);
			}
		}	
	}
}
