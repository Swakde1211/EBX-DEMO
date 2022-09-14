package com.cust.mdm.cust.usertask;

import com.orchestranetworks.service.OperationException;
import com.orchestranetworks.service.UserReference;
import com.orchestranetworks.workflow.UserTask;
import com.orchestranetworks.workflow.UserTaskWorkItemCompletionContext;

public class UserTaskManagement extends UserTask {
	public static final String STEWARD_VARIABLE_NAME = "steward";

	@Override
	public void handleWorkItemCompletion(final UserTaskWorkItemCompletionContext context) throws OperationException {
		final WorkItem workItem = context.getCompletedWorkItem();
		final UserReference user = workItem.getUserReference();
		final String userName = user.getUserId();
		String USER_NAME=context.getProcessInstanceCreator().getUserId();
		context.setVariableString(STEWARD_VARIABLE_NAME, USER_NAME);
		context.completeUserTask();
		
		
		
	}
}
