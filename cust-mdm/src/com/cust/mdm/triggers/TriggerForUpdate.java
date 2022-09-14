package com.cust.mdm.triggers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.cust.mdm.cust.path.CustPaths;
import com.onwbp.adaptation.Adaptation;
import com.onwbp.adaptation.AdaptationName;
import com.onwbp.adaptation.AdaptationTable;
import com.onwbp.adaptation.RequestResult;
import com.orchestranetworks.instance.ValueContext;
import com.orchestranetworks.ps.procedure.ModifyValuesProcedure;
import com.orchestranetworks.ps.util.AdaptationUtil;
import com.orchestranetworks.schema.ConstraintViolationException;
import com.orchestranetworks.schema.Path;
import com.orchestranetworks.schema.SchemaNode;
import com.orchestranetworks.schema.trigger.AfterModifyOccurrenceContext;
import com.orchestranetworks.schema.trigger.TableTrigger;
import com.orchestranetworks.schema.trigger.TriggerSetupContext;
import com.orchestranetworks.service.OperationException;
import com.orchestranetworks.service.ProcedureContext;

public class TriggerForUpdate extends TableTrigger {

	@Override
	public void handleAfterModify(AfterModifyOccurrenceContext context) throws OperationException {
		
		Adaptation inventoryRecord=context.getAdaptationOccurrence();
		if(inventoryRecord!=null)
		{
		Adaptation ItemRecord = AdaptationUtil.followFK(inventoryRecord, CustPaths._Root_Inventory._Root_Inventory_Item);
		if(ItemRecord!=null)
		{
			Map<Path,Object>map=new HashMap<>();
			map.put(CustPaths._Root_Item._Root_Item_DefaultPrice,((BigDecimal)ItemRecord.get(CustPaths._Root_Item._Root_Item_DefaultPrice)).add(new BigDecimal(12)));
			ModifyValuesProcedure mp=new ModifyValuesProcedure(ItemRecord);
			mp.setPathValueMap(map);

			mp.execute(context.getProcedureContext());
		
		}
	
		}
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void setup(TriggerSetupContext aContext) {
		// TODO Auto-generated method stub
		
	}

	
}
