package greboreda.graphqlexample.domain.person;

import greboreda.graphqlexample.business.ddd.Id;
import greboreda.graphqlexample.business.ddd.primitves.UUIDValue;

import java.util.UUID;

public class PersonId extends Id<UUIDValue> {

	private PersonId(UUIDValue value) {
		super(value);
	}

	public static PersonId aPersonId(UUID id) {
		return new PersonId(new UUIDValue(id));
	}


}
