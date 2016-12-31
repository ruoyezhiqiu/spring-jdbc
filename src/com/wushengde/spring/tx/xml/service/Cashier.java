package com.wushengde.spring.tx.xml.service;

import java.util.List;

public interface Cashier {
	/**
	 * 此方法表示：顾客结帐的操作，一般一次购物中顾客只执行该方法一次
	 */
	public void checkout(String username,List<Integer> isbns);

}
