package greboreda.graphqlexample.bussines.domain.valueobjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class PersonalNameTest {

	@Test
	@DisplayName("should concat ordered first names and last names")
	void test1() {
		final Collection<FirstName> firstNames = Arrays.asList(FirstName.from("Fidel"), FirstName.from("Alejandro"));
		final Collection<FamilyName> familyNames = Arrays.asList(FamilyName.from("Castro"), FamilyName.from("Ruz"));
		final PersonalName personalName = PersonalName.from(firstNames, familyNames);
		assertThat(personalName.getValue(), is("Fidel Alejandro Castro Ruz"));
	}

}