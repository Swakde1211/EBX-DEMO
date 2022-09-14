package com.cust.mdm.common.total;

import com.cust.mdm.cust.path.CustPaths;
import com.onwbp.adaptation.Adaptation;
import com.orchestranetworks.schema.ValueFunction;
import com.orchestranetworks.schema.ValueFunctionContext;
import java.math.BigDecimal;
import java.nio.file.Paths;

public class TotalField implements ValueFunction {
	private String path1;
	
	public String getPath1() {
		return path1;
	}

	public void setPath1(String path1) {
		System.out.println("Total field setter is called");
		this.path1 = path1;
	}
	
	
	public TotalField() {
		System.out.println("Total field constructor is called");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setup(final ValueFunctionContext context) {
// Nothing to do
		
	}

	@Override
	public Object getValue(final Adaptation inventory) {
		System.out.println("Get Value for total field");
		final Integer stock = (Integer) inventory.get(CustPaths._Root_Inventory._Root_Inventory_Stock);
		final BigDecimal price = (BigDecimal) inventory.get(CustPaths._Root_Inventory._Root_Inventory_Price);
		
		
		//System.out.println("my parent is"+inventory.getHome());-- dataspace deta
		//System.out.println("my parent is"+inventory.getContainerTable()); --- path deto n xsd location
		System.out.println("my getmodulename parent is"+inventory.getModuleName());//we get modules name eg cust.mdm
		System.out.println("get container is"+inventory.getContainer());//returns dataset and dataspace name
		System.out.println("get container is"+inventory.getAdaptationName().getStringName());
		System.out.println("get adaptaionName is"+inventory.getAdaptationName());
		System.out.println("get creator is"+inventory.getCreator());
		System.out.println("get lastUser is"+inventory.getLastUser());//
		System.out.println("get owner is"+inventory.getOwner());//gives name of owner
		System.out.println("get parent is"+inventory.getParent());
		System.out.println("get treeReference is"+inventory.getTreeReference());
		//System.out.println("get validationreport is"+inventory.getValidationReport(true,true));
		
		
		
			
		if (stock == null || price == null) {
			return null;
		}
		 BigDecimal total = price.multiply(new BigDecimal(stock));
		if(stock > 50)
			total=total.multiply(new BigDecimal(0.8));
		return total;
	}
}


