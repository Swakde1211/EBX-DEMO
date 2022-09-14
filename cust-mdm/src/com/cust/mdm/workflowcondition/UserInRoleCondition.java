package com.cust.mdm.workflowcondition;

import com.onwbp.base.text.Severity;
import com.onwbp.base.text.UserMessage;
import com.orchestranetworks.instance.Repository;
import com.orchestranetworks.ps.recordscomparison.Messages;
import com.orchestranetworks.service.OperationException;
import com.orchestranetworks.service.Role;
import com.orchestranetworks.service.UserReference;
import com.orchestranetworks.service.directory.DirectoryHandler;
import com.orchestranetworks.workflow.ConditionBean;
import com.orchestranetworks.workflow.ConditionBeanContext;



public class UserInRoleCondition extends ConditionBean {
private String userName;
private String roleName;
public final void setUserName(final String userName) {
this.userName = userName;
}
public final void setRoleName(final String roleName) {
this.roleName = roleName;
}

@Override
public boolean evaluateCondition(final ConditionBeanContext context) throws OperationException {
UserReference user = null;

try {
	user = toUser(this.userName);
} catch (Exception e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
Role role = null;
try {
	role = toRole(this.roleName);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
final Repository repository = Repository.getDefault();
final DirectoryHandler directoryHandler = DirectoryHandler.getInstance(repository);
final boolean result = directoryHandler.isUserInRole(user, role);
return result;
}

private static UserReference toUser(final String userName) throws Exception {
try {
final UserReference user = UserReference.forUser(userName);
return user;
} catch (final Exception exception) {
throw new Exception("User doesnt exist");
}
}

private static Role toRole(final String roleName) throws Exception {
try {
final Role role = Role.forSpecificRole(roleName);
return role;
} catch (final Exception exception) {
	throw new Exception("Role doesnt exist");

}
}
}




