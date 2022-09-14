/*
 * Copyright Orchestra Networks 2000-2012. All rights reserved.
 */
package com.cust.mdm.cust.schemaextension;

import com.cust.mdm.cust.path.*;
import com.orchestranetworks.ps.accessrule.*;
import com.orchestranetworks.schema.*;

/**
 */
public class CustReferenceSchemaExtension implements SchemaExtensions
{
	@Override
	public void defineExtensions(SchemaExtensionsContext context)
	{
		AccessRulesManager manager = new AccessRulesManager(context);
		manager.setAccessRuleOnNodeAndAllDescendants(
			CustReferencePaths._Root,
			false,
			new WorkflowAccessRule());
	}
}
