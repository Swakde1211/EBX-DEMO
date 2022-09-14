
package com.cust.mdm.common.totalcom.cust.mdm.permission;

import java.nio.file.Paths;

import com.cust.mdm.assignment.UserDeclaration;
import com.cust.mdm.cust.path.CustPaths;
import com.orchestranetworks.ps.servicepermission.AssociationActionAllowedBasedOnFieldServicePermissionRule;
import com.orchestranetworks.ps.servicepermission.ServicePermissionRulesManager;
import com.orchestranetworks.ps.servicepermission.ServicePermissionRulesManager.CompoundRuleEntityType;
import com.orchestranetworks.schema.Path;
import com.orchestranetworks.schema.SchemaExtensions;
import com.orchestranetworks.schema.SchemaExtensionsContext;
import com.orchestranetworks.service.AccessRule;
import com.orchestranetworks.service.OperationException;
import com.orchestranetworks.service.ServiceKey;

import HelloWorld.HelloWorldServiceDeclaration;

public class StoreExtentions implements SchemaExtensions {
	@Override
	public void defineExtensions(final SchemaExtensionsContext context) {
		final Path path = CustPaths._Root_Inventory.getPathInSchema()
				.add(CustPaths._Root_Inventory._Root_Inventory_Price);
		final AccessRule accessRule = new Permissionss();
		context.setAccessRuleOnNode(path, accessRule);

		ServicePermissionRulesManager sessManager = new ServicePermissionRulesManager(context, "");

		/*
		 * sessManager.setServicePermissionRuleOnNode(
		 * CustomerPaths._Root_Customer.getPathInSchema().add(CustomerPaths.
		 * _Root_Customer._Contacts), ServiceKey.CREATE, new
		 * AssociationActionAllowedBasedOnFieldServicePermissionRule(CustomerPaths.
		 * _Root_Customer._CompanyInformation_Active,Boolean.TRUE),
		 * CompoundRuleEntityType.ASSOCIATION);
		 * 
		 */
		try {
			sessManager.setServicePermissionRuleOnNode(
					CustPaths._Root_Item.getPathInSchema().add(CustPaths._Root_Item._Root_Item_Inventories),
					ServiceKey.MASSDELETE, new ItemAssosiation(), CompoundRuleEntityType.ASSOCIATION);
	
			sessManager.setServicePermissionRuleOnNode(
					CustPaths._Root_Item.getPathInSchema().add(CustPaths._Root_Item._Root_Item_Inventories),
					ServiceKey.CREATE, new ItemAssosiation(), CompoundRuleEntityType.ASSOCIATION);
		
		
		} catch (OperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		context.registerUserService(new HelloWorldServiceDeclaration());

	}

}
