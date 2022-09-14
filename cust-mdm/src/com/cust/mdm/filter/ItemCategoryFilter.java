package com.cust.mdm.filter;

import com.cust.mdm.cust.path.CustPaths;
import com.onwbp.adaptation.Adaptation;
import com.onwbp.adaptation.AdaptationTable;
import com.onwbp.adaptation.RequestResult;
import com.orchestranetworks.instance.ValueContext;
import com.orchestranetworks.ps.recordscomparison.Messages;
import com.orchestranetworks.schema.InvalidSchemaException;
import com.orchestranetworks.schema.SchemaNode;
import com.orchestranetworks.schema.TableRefFilter;
import com.orchestranetworks.schema.TableRefFilterContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class ItemCategoryFilter implements TableRefFilter {
	
	HashMap<String, Integer> map = new HashMap<>();

	@Override
	public void setup(final TableRefFilterContext context) {

	}

	@Override
	public String toUserDocumentation(final Locale locale, final ValueContext context) throws InvalidSchemaException {
		return Messages.get(ItemCategoryFilter.class, locale, "TheCategoryMustBeALeafCategory");
	}

	@Override
	public boolean accept(final Adaptation category, final ValueContext context) {

		final String name = (String) category.get(CustPaths._Root_Item._Root_Item_Name);

		final Integer identifier = (Integer) category.get(CustPaths._Root_Item._Root_Item_Identifier);
		
		if (map.isEmpty()) {
			map.put(name, identifier);
			return true;
		} else if (!map.containsKey(name)) {
			map.put(name, identifier);

			return true;
		} else if (map.get(name).equals(identifier)) {
			return true;

		} else {
			return false;
		}
	}

}
