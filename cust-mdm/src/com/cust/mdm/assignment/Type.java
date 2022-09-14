package com.cust.mdm.assignment;

import com.cust.mdm.CustAssign.Mypaths;
import com.onwbp.adaptation.Adaptation;
import com.onwbp.adaptation.AdaptationTable;
import com.onwbp.adaptation.RequestResult;
import com.orchestranetworks.instance.ValueContext;
import com.orchestranetworks.ps.recordscomparison.Messages;
import com.orchestranetworks.schema.InvalidSchemaException;
import com.orchestranetworks.schema.SchemaNode;
import com.orchestranetworks.schema.TableRefFilter;
import com.orchestranetworks.schema.TableRefFilterContext;
import java.util.Locale;


public class Type implements TableRefFilter{

	@Override
	public void setup(final TableRefFilterContext context) {
	final SchemaNode CustomerAddressNode = context.getSchemaNode();
	final SchemaNode categoriesNode = CustomerAddressNode.getNode(Mypaths._Root_Customer.getPathInSchema());
	context.addDependencyToInsertDeleteAndModify(categoriesNode);
	}

	@Override
	public String toUserDocumentation(final Locale locale, final ValueContext context)
	throws InvalidSchemaException {
	return Messages.get(Type.class, locale, "TheCategoryMustBeALeafCategory");
	}
	@Override
	public boolean accept(final Adaptation category, final ValueContext context) {
	final Integer CustomerIdentifier = (Integer)  category.get(Mypaths._Root_Customer._Root_Customer_ID);
	final AdaptationTable categories = category.getContainerTable();
	final String predicate =Mypaths._Root_Customer._Root_Customer_CustomerMain_Type.format() + " = '" + CustomerIdentifier + "'";
	final RequestResult result = categories.createRequestResult(predicate);
	return result.isEmpty();
	}
	
}
