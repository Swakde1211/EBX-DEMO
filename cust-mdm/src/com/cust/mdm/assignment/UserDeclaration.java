package com.cust.mdm.assignment;

import com.cust.mdm.CustAssign.Mypaths;
import com.orchestranetworks.service.ServiceKey;
import com.orchestranetworks.ui.selection.TableViewEntitySelection;
import com.orchestranetworks.userservice.UserService;
import com.orchestranetworks.userservice.declaration.ActivationContextOnTableView;
import com.orchestranetworks.userservice.declaration.UserServiceDeclaration;
import com.orchestranetworks.userservice.declaration.UserServicePropertiesDefinitionContext;
import com.orchestranetworks.userservice.declaration.WebComponentDeclarationContext;

public class UserDeclaration implements UserServiceDeclaration.OnTableView{
	@Override
	public UserService<TableViewEntitySelection> createUserService() {
		// TODO Auto-generated method stub
		return new CountRecordService();
		
	}
	
	@Override
	public void defineActivation(ActivationContextOnTableView context) {
		// TODO Auto-generated method stub
		context.includeSchemaNodesMatching(Mypaths._Root_Customer.getPathInSchema());
}

	@Override
	public void defineProperties(UserServicePropertiesDefinitionContext context) {
		// TODO Auto-generated method stub
		context.setLabel("Count Updates Records");
		
	}

	@Override
	public ServiceKey getServiceKey() {
		// TODO Auto-generated method stub
		return ServiceKey.forName("count_updated");
		
	}


	@Override
	public void declareWebComponent(WebComponentDeclarationContext aContext) {
		// TODO Auto-generated method stub
		
	}

}
