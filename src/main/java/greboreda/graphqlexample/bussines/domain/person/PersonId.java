package greboreda.graphqlexample.bussines.domain.person;

import greboreda.graphqlexample.bussines.domain.ddd.Id;

public class PersonId implements Id<Integer> {

	private final Integer value;

	private PersonId(Integer value) {
		if(value == null) {
			throw new NullPointerException("value can not be null");
		}
		if(value <= 0) {
			throw new IllegalArgumentException("PersonIds must have vale greater than zero");
		}
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

