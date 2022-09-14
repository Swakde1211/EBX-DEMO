package com.cust.mdm.assignment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.cust.mdm.CustAssign.Mypaths;
import com.cust.mdm.CustAssign.Mypaths1;
import com.onwbp.adaptation.Adaptation;
import com.orchestranetworks.schema.ValueFunction;
import com.orchestranetworks.schema.ValueFunctionContext;

public class CreateDifference implements ValueFunction{

private int diff;
	
	public int getAge() {
		
		return diff;
	}

	public void setAge(int diff) {
		System.out.println("age is "+diff);
		this.diff = diff;
	}
	
	

	public CreateDifference() {
		System.out.println("constructor for age class");
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public Object getValue(Adaptation records) {
		Date current_date=(Date)records.get(Mypaths._Root_Customer._Root_Customer_CustomerMain_CreatedDate);
		
		SimpleDateFormat formatter = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

		try {
			current_date = formatter.parse(current_date.toString());
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Calendar CurrentDate = Calendar.getInstance();
		CurrentDate.setTime(current_date);
		Calendar todays_date=Calendar.getInstance();
		
		int TodaysDay=CurrentDate.get(Calendar.DAY_OF_MONTH);
		int currentDay=todays_date.get(Calendar.DAY_OF_MONTH);
		int count=0;

		if(TodaysDay<=currentDay)
		{
			count=TodaysDay-currentDay;
		}
		
		
		return count;

		
	}

	@Override
	public void setup(ValueFunctionContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
