package com.cust.mdm.constratints;

import java.util.Locale;

import com.orchestranetworks.instance.ValueContext;
import com.orchestranetworks.instance.ValueContextForValidation;
import com.orchestranetworks.schema.Constraint;
import com.orchestranetworks.schema.ConstraintContext;
import com.orchestranetworks.schema.InvalidSchemaException;

public class ContraintOnPrice implements Constraint<Integer>{
 private Integer num;

public Integer getNum() {
	return num;
}

public void setNum(Integer num) {
	this.num = num;
}

@Override
public void checkOccurrence(Integer value, ValueContextForValidation arg1) throws InvalidSchemaException {
	// TODO Auto-generated method stub
	System.out.println("I am called here on price");
	
	if(value>500)
	{
		arg1.addWarning("your price is greater than 500");
		arg1.addMessage(null);
		
	}
}

@Override
public void setup(ConstraintContext arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public String toUserDocumentation(Locale arg0, ValueContext arg1) throws InvalidSchemaException {
	// TODO Auto-generated method stub
	return null;
}
 
}
