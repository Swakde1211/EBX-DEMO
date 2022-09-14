package com.cust.mdm.triggers;

import com.onwbp.adaptation.Adaptation;
import com.orchestranetworks.schema.trigger.AfterCreateOccurrenceContext;
import com.orchestranetworks.schema.trigger.AfterModifyOccurrenceContext;
import com.orchestranetworks.schema.trigger.TableTrigger;
import com.orchestranetworks.schema.trigger.TriggerSetupContext;
import com.orchestranetworks.service.OperationException;

public class TriggerFilters extends TableTrigger{

	@Override
	public void handleAfterCreate(AfterCreateOccurrenceContext context) throws OperationException {
		
	}

	@Override
	public void setup(TriggerSetupContext aContext) {
		// TODO Auto-generated method stub
		
	}

	

}
