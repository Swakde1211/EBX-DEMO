/*
 * package com.cust.mdm.assignment;
 * 
 * import com.cust.mdm.CustAssign.Mypaths; import
 * com.orchestranetworks.service.ServiceKey; import
 * com.orchestranetworks.ui.selection.TableViewEntitySelection; import
 * com.orchestranetworks.userservice.UserService; import
 * com.orchestranetworks.userservice.declaration.ActivationContextOnTableView;
 * import com.orchestranetworks.userservice.declaration.UserServiceDeclaration;
 * import com.orchestranetworks.userservice.declaration.
 * UserServicePropertiesDefinitionContext; import
 * com.orchestranetworks.userservice.declaration.WebComponentDeclarationContext;
 * 
 * public class UpdateWorkflowDeclaration implements
 * UserServiceDeclaration.OnTableView{
 * 
 * @Override public void declareWebComponent(WebComponentDeclarationContext
 * aContext) { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void defineActivation(ActivationContextOnTableView context)
 * { // TODO Auto-generated method stub
 * context.includeSchemaNodesMatching(Mypaths._Root_Customer.getPathInSchema());
 * 
 * 
 * }
 * 
 * @Override public void defineProperties(UserServicePropertiesDefinitionContext
 * context) { // TODO Auto-generated method stub
 * context.setLabel("Launch the workflow");
 * 
 * }
 * 
 * @Override public ServiceKey getServiceKey() {
 * 
 * return ServiceKey.forModuleServiceName("cust-mdm", "launchworkflow");
 * 
 * }
 * 
 * 
 * 
 * }
 * 
 * 
 * 
 * 
 */


 package com.cust.mdm.assignment;
import com.cust.mdm.CustAssign.Mypaths;
import com.orchestranetworks.ps.service.*;
import com.orchestranetworks.ps.servicepermission.CompoundServicePermissionRule;
import com.orchestranetworks.ps.workflow.WorkflowLauncherService;
import com.orchestranetworks.service.ServiceKey;
import com.orchestranetworks.ui.selection.TableViewEntitySelection;
import com.orchestranetworks.userservice.UserService;
import com.orchestranetworks.userservice.declaration.*;

public class UpdateWorkflowDeclaration implements UserServiceDeclaration.OnTableView {
	// public static final ServiceKey SERVICE_KEY =
	// ServiceKey.forModuleServiceName("client-mdm",
	// "UpdateSiteWorkflow");
//	public static final ServiceKey SERVICE_KEY = ServiceKey.forName("UpdateSiteWorkFlow");
//	private static final String SERVICE_LABEL = "Update Site Workflow";
//
//	public UpdateRecordServiceDeclaration() {
//		super(SERVICE_KEY, null, SERVICE_LABEL, null, null);
//	}
	@Override
	public UserService<TableViewEntitySelection> createUserService() {
//		WorkflowLauncherService<TableViewEntitySelection> service = new WorkflowLauncherService<>();
//		service.setWorkflowName("siteUpdateWorkflow");
//		return service;
		return new FiinalDeclaration();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void defineActivation(final ActivationContextOnTableView context) {
		// context.set
		context.includeSchemaNodesMatching(Mypaths._Root_Customer.getPathInSchema());
//		CompoundServicePermissionRule CompoundServicePermissionRule = new CompoundServicePermissionRule<>();
//		//CompoundServicePermissionRule.appendRule(new NotAvailableInWorkflowServicePermissionRule());
//		CompoundServicePermissionRule
//				.appendRule(new AvailableOnTablesPermissionRule(CustomerPaths._Root_Customer.getPathInSchema()));
//		CompoundServicePermissionRule.appendRule(
//				new AvailableToProfilePermissionRule(Role.forSpecificRole(Customer.ROLE_CUST_DATA_SELF_APPROVER)));
//		context.setPermissionRule(CompoundServicePermissionRule);
//		context.setDisplayForAllLocations(ActionDisplaySpec.HIDDEN);
	}
	@Override
	public void declareWebComponent(WebComponentDeclarationContext aContext) {
		// TODO Auto-generated method stub
	}
	@Override
	public void defineProperties(UserServicePropertiesDefinitionContext aContext) {
		// TODO Auto-generated method stub
	}
	@Override
	public ServiceKey getServiceKey() {
		// TODO Auto-generated method stub
		return ServiceKey.forModuleServiceName("cust-mdm", "UpdateRecordService");
	}
}