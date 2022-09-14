package com.cust.mdm.assignment;

import java.util.function.Consumer;
import javax.ws.rs.ApplicationPath;
import com.orchestranetworks.rest.ApplicationConfigurator;
import com.orchestranetworks.rest.RESTApplicationAbstract;
import com.orchestranetworks.rest.annotation.Documentation;

@ApplicationPath(RESTApplicationAbstract.REST_DEFAULT_APPLICATION_PATH)
@Documentation("My REST sample application")
public class registerRESTApplication extends RESTApplicationAbstract{
	public registerRESTApplication() {
		   // Adds one or more package names which will be used to scan for components.
	      super((cfg) -> cfg.addPackages(registerRESTApplication.class.getPackage()));		// TODO Auto-generated constructor stub
	}
}




