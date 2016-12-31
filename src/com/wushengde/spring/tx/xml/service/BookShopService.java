package com.wushengde.spring.tx.xml.service;

public interface BookShopService {
	/**
	 * 
	 * 此方法表示：顾客买书的操作，此操作顾客可以执行很多次。
	 */
	public void purchase(String username,Integer isbn);
}
