package com.cust.mdm.assignment;

import com.cust.mdm.CustAssign.Mypaths;
import com.cust.mdm.common.totalcom.cust.mdm.permission.ItemAssosiation;
import com.cust.mdm.common.totalcom.cust.mdm.permission.Permissionss;
import com.cust.mdm.cust.path.CustPaths;
import com.orchestranetworks.ps.admin.devartifacts.service.ExportDevArtifactsPropertiesFileDSDeclaration;
import com.orchestranetworks.ps.admin.devartifacts.service.ImportDevArtifactsPropertiesFileDSDeclaration;
import com.orchestranetworks.ps.servicepermission.ServicePermissionRulesManager;
import com.orchestranetworks.ps.servicepermission.ServicePermissionRulesManager.CompoundRuleEntityType;
import com.orchestranetworks.schema.Path;
import com.orchestranetworks.schema.SchemaExtensions;
import com.orchestranetworks.schema.SchemaExtensionsContext;
import com.orchestranetworks.service.AccessRule;
import com.orchestranetworks.service.OperationException;
import com.orchestranetworks.service.ServiceKey;

import HelloWorld.HelloWorldServiceDeclaration;

public class Permissions implements SchemaExtensions{

	@Override
	public void defineExtensions(SchemaExtensionsContext context) {
		// TODO Auto-generated method stub
		
		final Path path = Mypaths._Root_Customer.getPathInSchema();
		final AccessRule accessRule = new PermissionCondition();
		context.setAccessRuleOnOccurrence(path, accessRule);
		context.registerUserService(new UserDeclaration());
		context.registerUserService(new FiinalDeclaration());
		}

}
