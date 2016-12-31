package com.wushengde.spring.tx;

public interface BookShopDao {
	
	//根据书号查询书的单价：
	public int findBookPriceByIsbn(Integer isbn);
	
	//根据书号更新书的库存：使书号对应的库存 -1：
	public void updateBookStock(Integer isbn);
	
	//更新用户的账户余额：使username的：balance-price
	public void updateUserAccount(String username,int price);
}
