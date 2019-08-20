package com.ph;

import java.util.Arrays;
import java.util.List;

public class HelloArrays {
	
	public static void main(String[] args) {
		char[] letters=  { 'b', 'd', 'e', 'a', 'c' };

		int location = Arrays.binarySearch(letters, 'c');
		System.out.println(location);

		Arrays.sort(letters);
		System.out.println(letters);
		
		location = Arrays.binarySearch(letters, 'c');
		System.out.println(location);
		
		Arrays.fill(letters, 'z');
		System.out.println(letters);
		
		String[] stringArr = {"maru", "mary"};
		List<String> stringList = Arrays.asList(stringArr);
		stringList.forEach(System.out::print);
	}
}
