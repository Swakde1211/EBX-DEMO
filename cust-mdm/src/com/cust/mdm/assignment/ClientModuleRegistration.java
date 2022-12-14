package com.cust.mdm.assignment;

import javax.servlet.annotation.WebListener;

import com.orchestranetworks.module.ModuleContextOnRepositoryStartup;
import com.orchestranetworks.module.ModuleInitializedContext;
import com.orchestranetworks.module.ModuleRegistrationListener;
import com.orchestranetworks.module.ModuleServiceRegistrationContext;
import com.orchestranetworks.ps.admin.devartifacts.service.ExportDevArtifactsPropertiesFileDSDeclaration;
import com.orchestranetworks.ps.admin.devartifacts.service.ImportDevArtifactsPropertiesFileDSDeclaration;
import com.orchestranetworks.service.OperationException;

@WebListener
public class ClientModuleRegistration extends ModuleRegistrationListener {

	@Override
	public void handleContextInitialized(ModuleInitializedContext aContext) {
		// TODO Auto-generated method stub
		// aContext.registerRESTApplication(registerRESTApplication.class);
	}

	@Override
	public void handleServiceRegistration(ModuleServiceRegistrationContext context) {
		context.registerUserService(new ImportDevArtifactsPropertiesFileDSDeclaration("cust-mdm"));
		context.registerUserService(new ExportDevArtifactsPropertiesFileDSDeclaration("cust-mdm"));

		super.handleServiceRegistration(context);

	}

}
