package com.wushengde.spring.tx.xml.serviceimpl;

import java.util.List;

import com.wushengde.spring.tx.xml.service.BookShopService;
import com.wushengde.spring.tx.xml.service.Cashier;

public class CashierImpl implements Cashier {
	

	private BookShopService bookShopService;
	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}

	

	@Override
	public void checkout(String username, List<Integer> isbns) {
		for(Integer isbn:isbns){
			bookShopService.purchase(username, isbn);
		}
	}

}
