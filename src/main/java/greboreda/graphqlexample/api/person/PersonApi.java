package greboreda.graphqlexample.api.person;

import greboreda.graphqlexample.api.ApiObject;

import java.time.LocalDate;

public class PersonApi implements ApiObject {

	public String name;
	public LocalDate birthDate;
}
