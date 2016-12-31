package com.wushengde.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDao bookShopDao;
	
	
	//添加事务注解：用@Transactional注解
	@Transactional
	@Override
	public void purchase(String username, Integer isbn) {
		
		//1.获取书的单价：
		int price=bookShopDao.findBookPriceByIsbn(isbn);
		
		//2.更新书的库存：
		bookShopDao.updateBookStock(isbn);
		
		//3.更新用户账户的余额：
		bookShopDao.updateUserAccount(username, price);
	}

}
