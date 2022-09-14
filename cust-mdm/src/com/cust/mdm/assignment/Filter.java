package com.cust.mdm.assignment;

import java.util.Locale;

import com.cust.mdm.CustAssign.Mypaths;
import com.cust.mdm.CustAssign.Mypaths1;
import com.cust.mdm.cust.path.CustPaths;
import com.onwbp.adaptation.Adaptation;
import com.orchestranetworks.instance.ValueContext;
import com.orchestranetworks.schema.InvalidSchemaException;
import com.orchestranetworks.schema.Path;
import com.orchestranetworks.schema.TableRefFilter;
import com.orchestranetworks.schema.TableRefFilterContext;

public class Filter implements TableRefFilter{

	@Override
	public boolean accept(Adaptation record, ValueContext context) {
		// TODO Auto-generated method stub
		
		
		final String country =(String)context.getValue(Path.PARENT.add(Path.PARENT).add(Mypaths._Root_Address._Root_Address_Address_Country));

		final String filterCountry=(String)record.get(Mypaths1._Root_Subdivision._Root_Subdivision_Countries);
		
		return country.equals(filterCountry);
		

		
	}

	@Override
	public void setup(TableRefFilterContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toUserDocumentation(Locale userLocale, ValueContext aContext) throws InvalidSchemaException {
		// TODO Auto-generated method stub
		return null;
	}

}
