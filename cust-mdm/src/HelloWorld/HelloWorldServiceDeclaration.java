package HelloWorld;

import com.orchestranetworks.service.ServiceKey;
import com.orchestranetworks.ui.selection.DatasetEntitySelection;
import com.orchestranetworks.userservice.UserService;
import com.orchestranetworks.userservice.declaration.ActivationContextOnDataset;
import com.orchestranetworks.userservice.declaration.UserServiceDeclaration;
import com.orchestranetworks.userservice.declaration.UserServicePropertiesDefinitionContext;
import com.orchestranetworks.userservice.declaration.WebComponentDeclarationContext;

public class HelloWorldServiceDeclaration implements UserServiceDeclaration.OnDataset
{
    // The service key identifies the user service. 
    private static final ServiceKey serviceKey = ServiceKey.forName("HelloWorld");
     
    public HelloWorldServiceDeclaration()
    {
    }
     
    @Override
    public ServiceKey getServiceKey()
    {
        return serviceKey;
    }
 
    @Override
    public UserService<DatasetEntitySelection> createUserService()
    {
        // Creates an instance of the user service.
        return new HelloWordService();
    }
 
    @Override
    public void defineActivation(ActivationContextOnDataset context)
    {
        // The service is activated for all datasets instanciated with
        // the associated data model (see next example).
    	
    }
 
    @Override
    public void defineProperties(UserServicePropertiesDefinitionContext aContext)
    {
        // This label is displayed in menus that can execute the user service.
        aContext.setLabel("Hello World Service");
    }
 
    @Override
    public void declareWebComponent(WebComponentDeclarationContext aContext)
    {
    }
}