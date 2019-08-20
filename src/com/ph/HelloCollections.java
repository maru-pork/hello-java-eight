package com.ph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HelloCollections {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		List<String> strings = new ArrayList<>();
		strings.add("A");
		strings.add("B");
		
		String[] stringArr = new String[2];
		strings.toArray(stringArr);
		for(int i=0; i<stringArr.length; i++) {
			System.out.println(stringArr[i]);
		}
		
		// Iterator
		Iterator<String> iter = strings.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		ListIterator<String> listIter = strings.listIterator();
		listIter.hasPrevious();
		
		// Comparable
		MyDate[] mydates = { new MyDate(12, 12, 1991), new MyDate(12, 13, 1991),  new MyDate(12, 14, 1990) };
		Arrays.sort(mydates);
		System.out.println("Ascending..");
		for(int i=0; i<mydates.length; i++) {
			System.out.println(mydates[i]);
		}
		
		Arrays.sort(mydates, new MyDateComparator());
		System.out.println("Descending..");
		for(int i=0; i<mydates.length; i++) {
			System.out.println(mydates[i]);
		}
		
		// Comparator
		List<MyDate> dateList = Arrays.asList( new MyDate(12, 12, 1991), new MyDate(12, 13, 1991),  new MyDate(12, 14, 1990));
		System.out.println("Descending..");
		Collections.sort(dateList, new MyDateComparator());
		dateList.forEach(d->System.out.println(d));
	}
}


class MyDate implements Comparable {
	private int day, month, year;

	public MyDate(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return day + " " + month + " " + year;
	}
	
	@Override
	public int compareTo(Object o) {
		// ascending
		MyDate d = (MyDate) o;
		if(year != d.year) {
			return year - d.year;
		}
		if(month != d.month) {
			return month - d.month;
		}
		if(day != d.day) {
			return day - d.day;
		}
		return 0;
	}
}

class MyDateComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		// descending
		MyDate d1 = (MyDate) o1;
		MyDate d2 = (MyDate) o2;
		
		if(d1.getYear() != d2.getYear()) {
			return d2.getYear() - d1.getYear();
		}
		if(d1.getMonth() != d2.getMonth()) {
			return d2.getMonth()- d1.getMonth();
		}
		if(d1.getDay() != d2.getDay()) {
			return d2.getDay() - d1.getDay();
		}
		return 0;
	}
	
}