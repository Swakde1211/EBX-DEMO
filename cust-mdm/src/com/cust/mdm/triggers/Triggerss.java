
package com.cust.mdm.triggers;

import java.nio.file.Paths;

import com.cust.mdm.cust.path.CustPaths;
import com.onwbp.adaptation.Adaptation;
import com.onwbp.adaptation.AdaptationName;
import com.onwbp.adaptation.AdaptationTable;
import com.onwbp.adaptation.RequestResult;
import com.orchestranetworks.instance.ValueContext;
import com.orchestranetworks.schema.trigger.AfterDeleteOccurrenceContext;
import com.orchestranetworks.schema.trigger.TableTrigger;
import com.orchestranetworks.schema.trigger.TriggerSetupContext;
import com.orchestranetworks.service.OperationException;
import com.orchestranetworks.service.ProcedureContext;

public class Triggerss extends TableTrigger {
	@Override
	public void setup(final TriggerSetupContext context) {
// Nothing to do

		if(no == null || no.equals(""))
			context.addWarning("bhai parameter pass karade");
	}

	public Triggerss() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String no;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Override
	public void handleAfterDelete(final AfterDeleteOccurrenceContext context) throws OperationException {
		final RequestResult inventories = collectInventories(context);
		deleteInventories(context, inventories);
		
		
	}

	private RequestResult collectInventories(final AfterDeleteOccurrenceContext context) {
		final ValueContext valueContext = context.getOccurrenceContext();
		final Integer itemIdentifier = (Integer) valueContext.getValue(CustPaths._Root_Item._Root_Item_Identifier);
		final Adaptation dataset = valueContext.getAdaptationInstance();
		final AdaptationTable inventories = dataset.getTable(CustPaths._Root_Inventory.getPathInSchema());
		final String predicate = CustPaths._Root_Inventory._Root_Inventory_Item.format() + " = '" + itemIdentifier
				+ "'";
		final RequestResult result = inventories.createRequestResult(predicate);
		return result;
	}

	private void deleteInventories(final AfterDeleteOccurrenceContext context, final RequestResult inventories)
			throws OperationException {
		try {
			final ProcedureContext procedureContext = context.getProcedureContext();

			for (Adaptation inventory; (inventory = inventories.nextAdaptation()) != null;) {
				final AdaptationName inventoryName = inventory.getAdaptationName();
				procedureContext.doDelete(inventoryName, false);
			}
		} finally {
			inventories.close();
		}
	}
}
