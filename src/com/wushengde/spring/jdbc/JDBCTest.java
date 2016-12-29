package com.wushengde.spring.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JDBCTest {

	private ClassPathXmlApplicationContext ctx=null;
	private JdbcTemplate jdbcTemplate;
	
	{
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate=(JdbcTemplate) ctx.getBean("jdbcTemplate");
	}
	
	
	
	/**
	 * 获取单个列的值，或做统计查询：
	 * 使用queryForObject(String sql, Class<Long> requiredType)方法
	 */
	@Test
	public void testQueryForObject2(){
		//此处统计数据库中有多少条记录：
		String sql="SELECT count(id) FROM employees";
		long count =jdbcTemplate.queryForObject(sql,Long.class);
		System.out.println(count);
	}
	
	
	
	/**
	 * 测试查询一组对象：查到实体类的集合
	 * 注意 此处调用的方法是：query(String sql, RowMapper<Employee> rowMapper, Object... args)
	 */
	@Test
	public void testQueryForList(){
		String sql="SELECT id,last_name lastName,email FROM employees WHERE id>?";
		RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees=jdbcTemplate.query(sql,rowMapper,5);
		System.out.println(employees);
	}
	
	
	
	/**
	 * 测试获取一个对象：从数据库获取一条记录后实际得到一个对象
	 * 注意：不能调用：queryForObject(String sql, Class<Employee> requiredType, Object... args)方法！
	 * 应该调用：queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)方法！
	 * 1.其中的 RowMapper参数指定如何去映射结果集的行，常用的实现类为：BeanPropertyRowMapper
	 * 2.使用SQL中列的别名完成列名和类的属性名的映射，例如：last_name lastName
	 * 3.JdbcTemplate 不支持级联属性。JdbcTemplate只是一个JDBC的小工具，而不是ORM框架！
	 */
	@Test
	public void testQueryForObject(){
		String sql="SELECT id,last_name lastName,email,dept_id 'department.id' FROM employees WHERE id=?";
		RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);
		Employee employee=jdbcTemplate.queryForObject(sql,rowMapper,1);
		System.out.println(employee);
	}
	
	
	
	//测试UPDATE方法的批量更新：执行批量更新、插入、删除
	@Test
	public void testBatchUpdate(){
		
		//批量插入数据：最后一个参数是：Object[] 的 List 类型
		String sql="INSERT INTO employees(last_name,email,dept_id) VALUES(?,?,?)";
		//定义一个Object类型的数组的集合：
		List<Object[]> batchArgs=new ArrayList<>();
		batchArgs.add(new Object[]{"AA","aa@163.com",1});
		batchArgs.add(new Object[]{"BB","bb@163.com",2});
		batchArgs.add(new Object[]{"CC","cc@163.com",3});
		batchArgs.add(new Object[]{"DD","dd@163.com",4});
		batchArgs.add(new Object[]{"ee","ee@163.com",5});
		jdbcTemplate.batchUpdate(sql, batchArgs);
		
	}
	
	
	
	// 测试UPDATE方法： 执行 INSERT ，UPDATE ， DELETE 操作
	@Test
	public void testUpdate(){
		//测试更改数据：
		String sql="UPDATE employees SET last_name= ? WHERE id=?";
		int count=jdbcTemplate.update(sql, "Jack",5);
		System.out.println("数据库中有:"+count+"行数据变更了。");
	}
	
	
	
	//测试数据库连接：确定是否可以获取连接：connection
	@Test
	public void testDataSource() throws SQLException {
		//此处配置不用id获取bean 可以避开强转
		DataSource dataSource=ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
