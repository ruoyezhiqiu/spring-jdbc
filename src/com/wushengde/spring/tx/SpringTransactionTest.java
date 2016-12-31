package com.wushengde.spring.tx;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTransactionTest {
	
	private ClassPathXmlApplicationContext ctx=null;
	private BookShopDao bookShopDao=null;
	private BookShopService bookShopService=null;
	private Cashier cashier=null;
	
	{
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopDao=ctx.getBean(BookShopDao.class);
		bookShopService=(BookShopService) ctx.getBean("bookShopService");
		cashier=(Cashier) ctx.getBean("cashier");
	}
	
	
	//测试事务的传播行为：测试顾客结帐，即顾客买了很多书
	@Test
	public void testTransactionalPropagation(){
		cashier.checkout("AA", Arrays.asList(1001,1002));
	}
	
	
	//测试顾客买书的方法：
	@Test
	public void testBookShopService(){
		bookShopService.purchase("AA", 1001);
	}
	//测试根据书号查看书的价格：
	@Test
	public void testBookShopDaoFindPirceByIsbn() {
		System.out.println(bookShopDao.findBookPriceByIsbn(1001));
	}
	//测试根据书号更新书的库存：使书号对应的库存 -1：
	@Test
	public void testBookShopDaoUpdateBookStock(){
		bookShopDao.updateBookStock(1001);
	}
	
	//测试更新用户的账户余额：使username的：balance-price
	@Test
	public void testBookShopDaoUpdateUserAccount(){
		bookShopDao.updateUserAccount("AA", 200);
	}

}
