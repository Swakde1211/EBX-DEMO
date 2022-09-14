package com.cust.mdm.common.module;

import javax.servlet.annotation.*;

import com.orchestranetworks.ps.module.*;

@WebListener
public class CustModuleRegistrationListener extends PSModuleRegistrationListener
{
	public static final String MODULE_NAME = "cust-mdm";

	public CustModuleRegistrationListener()
	{
		super(MODULE_NAME);
	}

}