package com.nit.basics;

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
		
		String sqldt = "1990-08-10"; //yyyy-mm-dd
		java.sql.Date sqlDate = java.sql.Date.valueOf(sqldt);
		System.out.println("SQL Date:: "+sqlDate);
		
		//Convert java.sql.Date class obj to java.util.Date class obj
		java.util.Date ud3 = sqlDate;
		//new way
		java.util.Date ud4 = new java.util.Date(sqlDate.getTime());
		System.out.println("new util Date:: "+ud3);
		System.out.println("new util Date:: "+ud4);
		
		//Converting java.sql.Date class obj (or) java.util.Date class obj to String date values
		SimpleDateFormat sdf4 = new SimpleDateFormat("dd-MMM-YY");
		String s4 = sdf4.format(sqlDate);//java.sql.Date
		String s5 = sdf4.format(ud3);//java.util.Date
		System.out.println("String Date value:: "+s4);
		System.out.println("String Date value:: "+s5);
	}

}
