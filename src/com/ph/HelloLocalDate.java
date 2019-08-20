package com.ph;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class HelloLocalDate {
	
	public static void p(Object o) {
		System.out.println(o);
	}
	
	public static void main(String[] args) {
		// LocalDate
		LocalDate now = LocalDate.now();
		p(now);
		LocalDate hiredDate = LocalDate.of(2019, Month.MAY, 2);
		p(hiredDate);
		LocalDate aWeekFromNow = now.plusWeeks(1);
		p(aWeekFromNow);
		
		// LocalTime
		LocalTime hiredTime = LocalTime.of(9, 36);
		p(hiredTime);
		LocalTime anHourAfterHiredTime = hiredTime.plusHours(1);
		p(anHourAfterHiredTime);
		
		// LocalDateTime
		LocalDateTime dateTimeHired = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		p(dateTimeHired);
		dateTimeHired = LocalDateTime.of(2019, Month.MAY, 2, 10, 00);
		p(dateTimeHired);
		
		// Period
		Period period = Period.of(3, 5, 12);
		LocalDate test = LocalDate.now().minus(period);
		p(test);
		Period duration  = Period.between(now,  hiredDate);
		p(duration); // P-3M-17D simula nung starting date ko :D
		
		// DateTimeFormatter
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		p(now.format(formatter));
		
		DateTimeFormatter patternFormatter = DateTimeFormatter.ofPattern("yyyy/dd/MM");
		p(now.format(patternFormatter));
		patternFormatter = DateTimeFormatter.ofPattern("dd ' in the year of ' yyyy");
		p(now.format(patternFormatter));
	}
}
