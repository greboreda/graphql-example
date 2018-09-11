package greboreda.graphqlexample.bussines.domain.valueobjects;

import org.apache.commons.lang3.Validate;

public class FamilyName implements Name {

	private final String value;

	public FamilyName(String value) {
		Validate.notBlank(value);
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	public static FamilyName from(String value) {
		return new FamilyName(value);
	}
}
