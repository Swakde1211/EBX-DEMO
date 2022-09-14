package com.cust.mdm.assignment;

import com.cust.mdm.CustAssign.Mypaths;
import com.orchestranetworks.ps.service.*;
import com.orchestranetworks.ps.servicepermission.CompoundServicePermissionRule;
import com.orchestranetworks.ps.workflow.*;
import com.orchestranetworks.service.*;
import com.orchestranetworks.ui.selection.*;
import com.orchestranetworks.userservice.*;
import com.orchestranetworks.userservice.declaration.*;

/**
 * Declares the Maintain Customer Organization launcher service
 */
public class WorkFlowLauncherService
    extends
    GenericServiceDeclaration<TableViewEntitySelection, ActivationContextOnTableView>
    implements UserServiceDeclaration.OnTableView
{
    public static final ServiceKey SERVICE_KEY = ServiceKey.forModuleServiceName("cust-mdm", 
        "Customer_Update_Workflow");
    private static final String SERVICE_LABEL = "Customer_Update_Workflow";
    
    public WorkFlowLauncherService()
    {
        super(SERVICE_KEY, null, SERVICE_LABEL, null, null);
        
    }
    @Override
    public UserService<TableViewEntitySelection> createUserService()
    {
        WorkflowLauncherService<TableViewEntitySelection> service = new WorkflowLauncherService<>();
        service.setWorkflowName("Customer_Update_Workflow");
        return service;
    }
    
   // @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
      public void defineActivation(final ActivationContextOnTableView context) {
      /*  CompoundServicePermissionRule CompoundServicePermissionRule = new CompoundServicePermissionRule<>();
        CompoundServicePermissionRule.appendRule(new ClientModuleRegistration());
      */  
        
        context.includeSchemaNodesMatching(Mypaths._Root_Customer.getPathInSchema());
      }
}