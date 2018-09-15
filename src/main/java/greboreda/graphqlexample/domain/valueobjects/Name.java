package greboreda.graphqlexample.domain.valueobjects;


import greboreda.graphqlexample.business.ddd.ValueObject;
import greboreda.graphqlexample.business.ddd.primitves.StringValue;

public class Name extends StringValue implements ValueObject {

	protected Name(String value) {
		super(value);
	}

	public static Name aName(String value) {
		return new Name(value);
	}


}
