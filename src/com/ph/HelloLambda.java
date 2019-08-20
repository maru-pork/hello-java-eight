package com.ph;

public class HelloLambda {
	
	public static double computeRaise(Payable p) {
		return p.raiseSalary(0.4);
	}
	
	public static String runNoParam(NoParam p) {
		return p.retString();
	}
	
	public static void runMultipleParam(MultipleParam p) {
		p.printString("Maru", "Mary", "Mar");
	}
	
	public static void main(String[] args) {
		// previous implementation
		Consultant consultant = new Consultant();
		double ret = computeRaise(consultant);
		System.out.println(ret);
		
		// with java 8 lambda expression
		double salary = 80_000;
		ret = computeRaise((double percentage) -> {
			return salary + (percentage * salary);
		});
		System.out.println(ret);
		
		// single parameter: parameter type is optional
		ret = computeRaise((percentage) -> {
			return salary + (percentage * salary);
		});
		System.out.println(ret);
		
		// single parameter: parentheses that surround the parameter are optional
		ret = computeRaise(percentage -> {
			return salary + (percentage * salary);
		});
		System.out.println(ret);
		
		// single parameter: return keyword and semicolon are optional
		ret = computeRaise(percentage-> salary + (percentage * salary));
		System.out.println(ret);

		// no parameter example
		String param = runNoParam(()->{
			return "Hello";
		});
		System.out.println(param);
		
		//multiple parameter example
		runMultipleParam((String a, String b, String c)->{
			System.out.println(a + " " + b + " " + c);
		});
	}
}

interface Payable {
	double raiseSalary(double percentage);
}

class Consultant implements Payable{
	private double salary = 80_000;
	@Override
	public double raiseSalary(double percentage) {
		return salary + (salary * percentage);
	}
}

interface NoParam {
	String retString();
}

interface MultipleParam {
	void printString(String a, String b, String c);
}
