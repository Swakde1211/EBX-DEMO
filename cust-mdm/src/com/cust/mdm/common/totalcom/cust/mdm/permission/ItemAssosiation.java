package com.cust.mdm.common.totalcom.cust.mdm.permission;

import com.onwbp.base.text.UserMessage;
import com.orchestranetworks.ps.constants.CommonConstants;
import com.orchestranetworks.ps.servicepermission.TechAdminOnlyServicePermissionRules;
import com.orchestranetworks.service.Role;
import com.orchestranetworks.ui.selection.AssociationEntitySelection;
import com.orchestranetworks.userservice.permission.ServicePermissionRuleContext;
import com.orchestranetworks.userservice.permission.UserServicePermission;

public class ItemAssosiation extends TechAdminOnlyServicePermissionRules.OnAssociation {

	@Override
	public UserServicePermission getPermission(ServicePermissionRuleContext<AssociationEntitySelection> context) {

		if (!context.getSession().isUserInRole(Role.forSpecificRole("Role1")))
		{
			return UserServicePermission.getEnabled();
		}
		return UserServicePermission.getDisabled(
				UserMessage
				.createError("Must be in role"+"role1"));
	}

}
