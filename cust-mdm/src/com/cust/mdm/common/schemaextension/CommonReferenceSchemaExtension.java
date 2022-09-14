/*
  * Copyright Orchestra Networks 2000-2012. All rights reserved.
 */
package com.cust.mdm.common.schemaextension;

import com.cust.mdm.common.path.*;
import com.orchestranetworks.ps.accessrule.*;
import com.orchestranetworks.schema.*;

/**
 */
public class CommonReferenceSchemaExtension implements SchemaExtensions
{
	@Override
	public void defineExtensions(SchemaExtensionsContext context)
	{
		AccessRulesManager manager = new AccessRulesManager(context);
		manager.setAccessRuleOnNodeAndAllDescendants(
			CommonReferencePaths._Root,
			false,
			new WorkflowAccessRule());
	}
}
