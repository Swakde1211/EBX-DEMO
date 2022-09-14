package com.cust.mdm.assignment;

import java.util.Calendar;

import com.onwbp.adaptation.Adaptation;
import com.orchestranetworks.schema.SchemaNode;
import com.orchestranetworks.service.AccessPermission;
import com.orchestranetworks.service.AccessRule;
import com.orchestranetworks.service.Role;
import com.orchestranetworks.service.Session;

public class PermissionCondition implements AccessRule{

	@Override
	public AccessPermission getPermission(Adaptation adaptation, Session session, SchemaNode aNode) {

		

		if (session.isUserInRole(Role.ADMINISTRATOR)) {
			return AccessPermission.getReadWrite();
		}

		
		/*
		 * final Date date=(Date)adaptation.getDate(Mypaths._Root_Customer.
		 * _Root_Customer_CustomerMain_CreatedDate);
		 * 
		 * SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yy"); String modd =
		 * formate.format(date);
		 * 
		 */
		Calendar current_date=Calendar.getInstance();
		int currentDay=current_date.get(Calendar.DAY_OF_MONTH);

		
		/*
		 * Date date = new Date(); LocalDate localDate =
		 * date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		 * 
		 * int day = localDate.getDayOfMonth();
		 */
		
		 if(currentDay >1 && currentDay<15 && session.getInteraction(true) == null)
		{
			return AccessPermission.getReadOnly();
		}
		return AccessPermission.getReadWrite();
	}

}
