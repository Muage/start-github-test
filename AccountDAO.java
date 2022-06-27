package helloworld;

import java.util.*;

public interface AccountDAO {
	public ArrayList<AccountVO> list();
	
	public void insert(AccountVO vo, ArrayList<AccountVO> array);
	
	public AccountVO read(int no, ArrayList<AccountVO> array);
	
	public void trade(int no, int amount, ArrayList<AccountVO> array);
}
