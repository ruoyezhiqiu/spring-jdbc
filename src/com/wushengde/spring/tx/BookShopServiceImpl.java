package com.wushengde.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDao bookShopDao;
	
	
	//添加事务注解：用@Transactional注解
	/**
	 * 
	 * 1.propagation属性:指定事务的传播行为，即：当前的事务方法被另外一个事务方法调用时，如何使用事务。
	 * 默认情况下取值为：REQUIRED 表示使用调用方法自带的事务，如果其方法不是事务方法，则自己新建一个事务，并在其中运行该事务。
	 * propagation取值为：REQUIRES_NEW时，表示新开一个事务。新开事务与被调用的事务方法的事务一起运行，相互隔离，互补影响。
	 * propagation常用取值就是以上两个值：REQUIRED与REQUIRES_NEW
	 * 
	 * 2.isolation属性:指定事务的隔离级别，最常用的取值为：READ_COMMITTED
	 * 
	 * 3.rollbackFor属性：表示指定事务在遇到什么异常时进行回滚，默认情况下spring的声明式事务对所有运行时异常进行回滚。也可以通过对应属性设置。
	 * 通常情况下，rollbackFor取默认值，不需要配置。
	 * 
	 * 4.readOnly属性:表示这个事务只读取数据但不更新数据, 这样可以帮助数据库引擎优化事务，若这个事务是只读数据不更新数据，应该设置readOnly=true。
	 * 
	 * 5.timeout属性：表示指定事务的强制回滚之前事务可以占用的时间，默认单位为：秒。此例中设置timeout为3秒。若事务方法在此时间内没有执行结束，将会被强制回滚。
	 * timeout：可以解决事务方法长时间为结束。
	 * 
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED,readOnly=false,timeout=3)
	@Override
	public void purchase(String username, Integer isbn) {
		
		try {
			//此处表示让线程沉睡2秒，sleep()中的参数单位为：毫秒，如果此处时间超过上面设置的timeout，事务将强制回滚，且不执行此事务方法中的所有方法。
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//1.获取书的单价：
		int price=bookShopDao.findBookPriceByIsbn(isbn);
		
		//2.更新书的库存：
		bookShopDao.updateBookStock(isbn);
		
		//3.更新用户账户的余额：
		bookShopDao.updateUserAccount(username, price);
	}

}
