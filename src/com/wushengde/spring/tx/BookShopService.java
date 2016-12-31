package com.wushengde.spring.tx;

public interface BookShopService {
	/**
	 * 
	 * 书店中顾客买书的方法
	 */
	public void purchase(String username,Integer isbn);
}
