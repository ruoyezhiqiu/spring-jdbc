package com.wushengde.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {
	
	@Autowired
	private JdbcTemplate jdbctemplate;

	@Override
	public int findBookPriceByIsbn(Integer isbn) {
		String sql="SELECT price FROM BOOK WHERE isbn=?";
		return jdbctemplate.queryForObject(sql, Integer.class, isbn);
	}

	@Override
	public void updateBookStock(Integer isbn) {
		
		//检查书的库存是否足够，若不够则抛出异常：
		String sql2="SELECT stock FROM book_stock WHERE isbn=?";
		int stock=jdbctemplate.queryForObject(sql2, Integer.class,isbn);
		if(stock==0){
			throw new BookStockException("库存不足！");
		}
		
		String sql="UPDATE book_stock SET stock= stock-1 WHERE isbn=?";
		jdbctemplate.update(sql,isbn);

	}

	@Override
	public void updateUserAccount(String username, int price) {
		//检查账户余额是否足够，若不足则抛出异常：
		String sql2="SELECT balance FROM account WHERE username=?";
		int balance=jdbctemplate.queryForObject(sql2, Integer.class,username);
		if(balance<price){
			throw new UserAccountException("账户余额不足，请及时充值！");
		}
		
		String sql="UPDATE account SET balance=balance-? WHERE username=?";
		jdbctemplate.update(sql, price,username);
	}

}
