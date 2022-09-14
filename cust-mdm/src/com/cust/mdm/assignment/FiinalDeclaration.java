package com.cust.mdm.assignment;

import com.cust.mdm.CustAssign.Mypaths;
import com.orchestranetworks.ps.service.GenericServiceDeclaration;
import com.orchestranetworks.ps.workflow.WorkflowLauncherService;
import com.orchestranetworks.service.ServiceKey;
import com.orchestranetworks.ui.selection.RecordEntitySelection;
import com.orchestranetworks.ui.selection.TableViewEntitySelection;
import com.orchestranetworks.userservice.UserService;
import com.orchestranetworks.userservice.UserServiceDisplayConfigurator;
import com.orchestranetworks.userservice.UserServiceEventOutcome;
import com.orchestranetworks.userservice.UserServiceObjectContextBuilder;
import com.orchestranetworks.userservice.UserServiceProcessEventOutcomeContext;
import com.orchestranetworks.userservice.UserServiceSetupDisplayContext;
import com.orchestranetworks.userservice.UserServiceSetupObjectContext;
import com.orchestranetworks.userservice.UserServiceValidateContext;
import com.orchestranetworks.userservice.declaration.ActivationContextOnRecord;
import com.orchestranetworks.userservice.declaration.ActivationContextOnTableView;
import com.orchestranetworks.userservice.declaration.UserServiceDeclaration;

public class FiinalDeclaration extends GenericServiceDeclaration<RecordEntitySelection, ActivationContextOnRecord>
		implements UserServiceDeclaration.OnRecord {
	//public static final ServiceKey SERVICE_KEY = ServiceKey.forModuleServiceName("cust-mdm",
	//		"Customer_Update_Workflow");
	public static final ServiceKey SERVICE_KEY = ServiceKey.forName(
			"Customer_Update_Workflow");
	private static final String SERVICE_LABEL = "Customer_Update_Workflow";

	public FiinalDeclaration() {
		super(SERVICE_KEY, null, SERVICE_LABEL, null, null);

	}

	@Override
	public UserService<RecordEntitySelection> createUserService() {
		WorkflowLauncherService<RecordEntitySelection> service = new WorkflowLauncherService<>();
		service.setXpathToTable(Mypaths._Root_Customer.getPathInSchema().format());
		service.setWorkflowName("Customer_Update_Workflow");
		return service;
	}

	@Override
	public void defineActivation(ActivationContextOnRecord context) {
		context.includeSchemaNodesMatching(Mypaths._Root_Customer.getPathInSchema());

	}

}
