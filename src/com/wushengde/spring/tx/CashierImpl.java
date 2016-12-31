package com.wushengde.spring.tx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cashier")
public class CashierImpl implements Cashier {
	
	@Autowired
	private BookShopService bookShopService;

	
	//此处表示一个事务调用另一个事务的方法
	@Transactional
	@Override
	public void checkout(String username, List<Integer> isbns) {
		for(Integer isbn:isbns){
			bookShopService.purchase(username, isbn);
		}
	}

}
