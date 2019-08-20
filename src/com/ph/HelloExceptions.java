package com.ph;

import java.sql.Connection;
import java.sql.SQLException;

public class HelloExceptions {
	
	public static void main(String[] args) {
		try (Connection conn = null) {
			// more sql code
		} catch (SQLException e) {
			// handle exception
		}
		
		try {
			String str = "a";
			char c = str.charAt(20);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(e.getCause()); // null
			System.out.println(e.getMessage()); // String index out of range: 20
			System.out.println(e.getStackTrace()); // [Ljava.lang.StackTraceElement;@15db9742
			e.printStackTrace();
		}
		
		try {
			withdraw(1);
		} catch (OverDraftException e) {
			System.out.println(e.getMessage());
		}
		
		exceptionRunTime();
	}
	
	public static void withdraw(double amount) throws OverDraftException{
		if(amount == 1) {
			throw new OverDraftException("insufficient funds");
		}
	}
	
	public static void exceptionRunTime() throws SampleException{
		throw new SampleException("i am a runtime exception");
	}
}

class OverDraftException extends Exception {
	public OverDraftException(String message) {
		super(message);
	}
}

class SampleException extends RuntimeException {
	public SampleException(String message) {
		super(message);
	}
}
