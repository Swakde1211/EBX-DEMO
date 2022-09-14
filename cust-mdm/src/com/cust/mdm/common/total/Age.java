package com.cust.mdm.common.total;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.cust.mdm.cust.path.CustPaths;
import com.onwbp.adaptation.Adaptation;
import com.onwbp.com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;
import com.orchestranetworks.schema.ValueFunction;
import com.orchestranetworks.schema.ValueFunctionContext;

public class Age implements ValueFunction{
	private int age;
	
	public int getAge() {
		
		return age;
	}

	public void setAge(int age) {
		System.out.println("setter for age ");
		System.out.println("age is "+age);
		this.age = age;
	}
	
	

	public Age() {
		System.out.println("constructor for age class");
		// TODO Auto-generated constructor stub
	}
	
	
	

	@Override
	public Object getValue(Adaptation records) {
		// TODO Auto-generated method stub
		Date dob=(Date)records.get(CustPaths._Root_AgeTable._Root_AgeTable_Date);		
		
		SimpleDateFormat formatter = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		try {
			dob = formatter.parse(dob.toString());
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.setTime(dob);
		Calendar current_date=Calendar.getInstance();
		
		int Birthyear=dateOfBirth.get(Calendar.YEAR);
		int currenthyear=current_date.get(Calendar.YEAR);
		
		int BirthMonth=dateOfBirth.get(Calendar.MONTH);
		int currentMonth=current_date.get(Calendar.MONTH);
		
		int BirthDay=dateOfBirth.get(Calendar.DAY_OF_MONTH);
		int currentDay=current_date.get(Calendar.DAY_OF_MONTH);
		
		int age =currenthyear-Birthyear - 1;
		
		if(BirthMonth<currentMonth)
		{
			age=age+1;		
		}
		else if(BirthMonth==currentMonth && BirthDay<=currentDay)
		{
			age=age+1;		
		}
		
		return age;
	}

	@Override
	public void setup(ValueFunctionContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("set up for age class");
	}

}
