package com.wushengde.spring.tx.xml.serviceimpl;

import com.wushengde.spring.tx.xml.BookShopDao;
import com.wushengde.spring.tx.xml.service.BookShopService;

public class BookShopServiceImpl implements BookShopService {
	
	
	private BookShopDao bookShopDao;
	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	
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
