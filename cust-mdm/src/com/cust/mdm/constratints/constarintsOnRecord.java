package com.cust.mdm.constratints;

import com.cust.mdm.cust.path.CustPaths;
import com.orchestranetworks.instance.ValueContext;
import com.orchestranetworks.instance.ValueContextForValidation;
import com.orchestranetworks.ps.recordscomparison.Messages;
import com.orchestranetworks.schema.Constraint;
import com.orchestranetworks.schema.ConstraintContext;
import com.orchestranetworks.schema.InvalidSchemaException;

import java.math.BigDecimal;
import java.util.Locale;

public class constarintsOnRecord implements Constraint<Integer> {

	private Integer number;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public void setup(final ConstraintContext context) {
// Constraint dependencies must not be declared,
// in case it is an inherited attribute.

	}

	@Override
	public String toUserDocumentation(final Locale locale, final ValueContext context) throws InvalidSchemaException {
		return Messages.get(constarintsOnRecord.class, locale, "ThePriceMustNotDifferFromTheDefaultPriceMoreThan{0}");
	}

	@Override
	public void checkOccurrence(final Integer value, final ValueContextForValidation context)
			throws InvalidSchemaException {

		// context.getNode();
		System.out.println("I am called here");
		if (value < 50) {
			System.out.println("you will not get discount");
			context.addError("You will not get discount");
		}

	}

}
