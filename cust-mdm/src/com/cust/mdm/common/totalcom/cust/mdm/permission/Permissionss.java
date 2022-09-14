package com.cust.mdm.common.totalcom.cust.mdm.permission;

import com.cust.mdm.cust.path.CustPaths;
import com.onwbp.adaptation.Adaptation;
import com.orchestranetworks.schema.SchemaNode;
import com.orchestranetworks.service.AccessPermission;
import com.orchestranetworks.service.AccessRule;
import com.orchestranetworks.service.Role;
import com.orchestranetworks.service.Session;

public class Permissionss implements AccessRule {
	public static final int INVENTORY_STOCK_THRESHOLD = 100;

	@Override
	public AccessPermission getPermission(final Adaptation adaptation, final Session session, final SchemaNode node) {
		
		
		if (adaptation.isSchemaInstance()) {
			return AccessPermission.getReadWrite();
		}

		if (session.isUserInRole(Role.ADMINISTRATOR)) {
			return AccessPermission.getReadWrite();
		}

		final int inventoryStock = adaptation.get_int(CustPaths._Root_Inventory._Root_Inventory_Stock);
		if (inventoryStock < INVENTORY_STOCK_THRESHOLD) {
			return AccessPermission.getReadWrite();
		}
		return AccessPermission.getHidden();
	}
}
