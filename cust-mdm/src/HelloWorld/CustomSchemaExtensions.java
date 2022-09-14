package HelloWorld;

import com.orchestranetworks.schema.SchemaExtensions;
import com.orchestranetworks.schema.SchemaExtensionsContext;

public class CustomSchemaExtensions implements SchemaExtensions
{
    @Override
    public void defineExtensions(SchemaExtensionsContext aContext)
    {
        // Register the service.
        aContext.registerUserService(new HelloWorldServiceDeclaration());
    }

	public void defineExtensions1(SchemaExtensionsContext arg0) {
		// TODO Auto-generated method stub
		
	}
}