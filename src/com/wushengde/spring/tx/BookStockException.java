package com.wushengde.spring.tx;
//定义一个书的库存的异常，此异常继承运行时异常：RuntimeException
public class BookStockException extends RuntimeException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookStockException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BookStockException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BookStockException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BookStockException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
