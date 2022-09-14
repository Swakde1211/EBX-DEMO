package com.orchestranetworks.ps.valuefunction;

import com.onwbp.adaptation.*;
import com.orchestranetworks.schema.*;

/**
 * 
 * Gets the user who created the record.
 * @author MCH
 */
public class CreationDateFunction implements ValueFunction
{

	/*
	 * @see com.orchestranetworks.schema.ValueFunction#getValue(com.onwbp.adaptation.Adaptation)
	 */
	@Override
	public Object getValue(final Adaptation pRecord)
	{

		return pRecord.getTimeOfCreation();
	}

	/*
	 * @see com.orchestranetworks.schema.ValueFunction#setup(com.orchestranetworks.schema.
	 * ValueFunctionContext)
	 */
	@Override
	public void setup(final ValueFunctionContext pContext)
	{
	}

}