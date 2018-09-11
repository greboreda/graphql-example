package greboreda.graphqlexample.bussines.domain.person;

import greboreda.graphqlexample.bussines.domain.ddd.Id;
import org.apache.commons.lang3.Validate;

public class PersonId implements Id<Integer> {

	private final Integer value;

	private PersonId(Integer value) {
		Validate.notNull(value);
		Validate.isTrue(value > 0, "value must be positive");
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	public static PersonId from(Integer value) {
		return new PersonId(value);
	}
}

