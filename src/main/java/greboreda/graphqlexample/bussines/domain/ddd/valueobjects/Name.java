package greboreda.graphqlexample.bussines.domain.ddd.valueobjects;

import greboreda.graphqlexample.bussines.domain.ddd.ValueObject;

public class Name implements ValueObject {

	private final String value;

	private Name(String value) {
		this.value = value;
	}

	public static Name from(String value) {
		return new Name(value);
	}
}
