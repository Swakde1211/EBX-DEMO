package com.cust.mdm.assignment;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.Days;

import com.cust.mdm.CustAssign.Mypaths;
import com.orchestranetworks.instance.ValueContext;
import com.orchestranetworks.schema.trigger.BeforeCreateOccurrenceContext;
import com.orchestranetworks.schema.trigger.BeforeModifyOccurrenceContext;
import com.orchestranetworks.schema.trigger.NewTransientOccurrenceContext;
import com.orchestranetworks.schema.trigger.TableTrigger;
import com.orchestranetworks.schema.trigger.TriggerSetupContext;
import com.orchestranetworks.service.OperationException;

public class TodaysDate extends  TableTrigger {
	@Override
	public void handleBeforeCreate(BeforeCreateOccurrenceContext context) throws OperationException {
		// TODO Auto-generated method stub
		
		
		context.getOccurrenceContextForUpdate().setValueEnablingPrivilegeForNode(new Date(), Mypaths._Root_Customer._Root_Customer_CustomerMain_CreatedDate); 

	}
	@Override
	public void handleBeforeModify(BeforeModifyOccurrenceContext context) throws OperationException {
		final ValueContext valueContext = context.getOccurrenceContext();
		final Date modifieddate = (Date) valueContext.getValue(Mypaths._Root_Customer._Root_Customer_CustomerMain_CreatedDate);
		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yy");
	       String modd = formate.format(modifieddate);
	      
		  Date date = new Date();
	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
	       String cur = formatter.format(date);
	       
	  try {
	 
	  Date dateBefore = formate.parse(modd);
      Date dateAfter = formate.parse(cur);
      int difference = (int) (dateAfter.getTime() - dateBefore.getTime());
      int daysBetween = Math.abs(difference / (1000*60*60*24));
      
       context.getOccurrenceContextForUpdate().setValue(daysBetween,Mypaths._Root_Customer._Root_Customer_CustomerMain_Create_difference_in_Days );
		
	  } catch (Exception e) {
	       e.printStackTrace();
	 }
	  

	  
	  
		
	}

	@Override
	public void setup(TriggerSetupContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleNewContext(NewTransientOccurrenceContext c) {
	
	}

	/*
	 * @Override public void handleNewContext(NewTransientOccurrenceContext context)
	 * { // TODO Auto-generated method stub
	 * context.getSession().getInteraction(true).getWorkItemKey() ;
	 * 
	 * }
	 */
	
	
	

}
