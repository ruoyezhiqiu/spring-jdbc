package com.wushengde.spring.jdbc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmployeeDao {

	private ClassPathXmlApplicationContext ctx;
	private EmployeeDao employeeDao;
	
	{
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		employeeDao=ctx.getBean(EmployeeDao.class);
	}
	
	@Test
	public void testEmployeeDao() {
		System.out.println(employeeDao.getEmployee(1));
	}

}
