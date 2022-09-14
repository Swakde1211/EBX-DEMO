package com.cust.mdm.cust.usertask;

import com.orchestranetworks.service.OperationException;
import com.orchestranetworks.service.UserReference;
import com.orchestranetworks.workflow.CreationWorkItemSpec;
import com.orchestranetworks.workflow.UserTask;
import com.orchestranetworks.workflow.UserTaskCreationContext;

public class UserTaskManagement2 extends UserTask {
	public static final String STEWARD_VARIABLE_NAME = "steward";

	@Override
	public void handleCreate(final UserTaskCreationContext context) throws OperationException {
		final String userName = context.getVariableString(STEWARD_VARIABLE_NAME);
		final UserReference user = UserReference.forUser(userName);
		final CreationWorkItemSpec creationWorkItemSpec = CreationWorkItemSpec.forAllocation(user);
		context.createWorkItem(creationWorkItemSpec);
		
	}
}