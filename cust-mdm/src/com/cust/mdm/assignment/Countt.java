package com.cust.mdm.assignment;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.Days;

import com.cust.mdm.CustAssign.Mypaths;
import com.orchestranetworks.instance.ValueContext;
import com.orchestranetworks.schema.trigger.BeforeModifyOccurrenceContext;
import com.orchestranetworks.schema.trigger.TableTrigger;
import com.orchestranetworks.schema.trigger.TriggerSetupContext;
import com.orchestranetworks.service.OperationException;

public class Countt  extends  TableTrigger{

	@Override
	public void setup(TriggerSetupContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleBeforeModify(BeforeModifyOccurrenceContext context) throws OperationException {
		final ValueContext valueContext = context.getOccurrenceContext();
		final String modifieddate = (String) valueContext.getValue(Mypaths._Root_Customer._Root_Customer_CustomerMain_CreatedDate);
		
		
		  Date date = new Date();
	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
	       String cur = formatter.format(date);
	       
	  System.out.println("date aaj ki"+cur);
	  
	  org.joda.time.LocalDate dateBefore = org.joda.time.LocalDate.parse(cur);
	  org.joda.time.LocalDate dateAfter = org.joda.time.LocalDate.parse(modifieddate);

	  int daysDiff = Math.abs(Days.daysBetween(dateBefore, dateAfter).getDays());

	  context.getOccurrenceContextForUpdate().setValue(daysDiff,Mypaths._Root_Customer._Root_Customer_CustomerMain_Create_difference_in_Days );
		

		
	}

	
}
