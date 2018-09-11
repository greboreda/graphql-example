package greboreda.graphqlexample.bussines.domain.valueobjects;

import org.apache.commons.lang3.Validate;

public class FirstName implements Name {

	private final String value;

	private FirstName(String value) {
		Validate.notBlank(value);
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	public static FirstName from(String value) {
		return new FirstName(value);
	}
}
