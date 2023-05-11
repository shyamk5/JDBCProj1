package com.nt.basics;

/*
 * 		JDBC App to Insert Data with Date Values in different formats
 */

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
 * 	Program to convert String date values to java.util.date and java.sql.date class obj
 */

public class DateValuesConversion {

	public static void main(String[] args) throws Exception {
		
		String s1 = "11-11-2000"; //dd-MM-YYYY
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		
		java.util.Date ud1 = sdf.parse(s1);
		
		System.out.println("String date value:: "+s1);
		System.out.println("util date:: "+ud1);
		
		//Converting Java.util.Date class obj to java.sql.Date class obj
		long ms = ud1.getTime();
		
		System.out.println("ms:: "+ms);
		java.sql.Date sd1 = new java.sql.Date(ms);
		System.out.println("util date:: "+ud1);
		System.out.println("sql date:: "+sd1);
		
		//if my String date value is having the pattern of YYYY-MM-dd then it is not required to convert into java.util.Date
		//directly we can convert it into java.sql.Date
		
		String sqldt = "1990-06-01"; //yyyy-mm-dd
		java.sql.Date sqlDate = java.sql.Date.valueOf(sqldt);
		System.out.println("SQL Date:: "+sqlDate);
	}

}
