package greboreda.graphqlexample.bussines.domain;


import greboreda.graphqlexample.bussines.domain.valueobjects.FamilyName;
import greboreda.graphqlexample.bussines.domain.valueobjects.FirstName;
import greboreda.graphqlexample.bussines.domain.valueobjects.PersonalName;
import greboreda.graphqlexample.bussines.domain.person.Person;
import greboreda.graphqlexample.bussines.domain.person.PersonId;
import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class PersonTest {

	@Test
	@DisplayName("should give current year birthday if given year birthday has not been passed")
	void test1() {
		final Person person = aPersonBornAt(LocalDate.of(1989, Month.NOVEMBER, 16));
		final LocalDate after = LocalDate.of(2000, Month.JANUARY, 1);

		assertThat(person.nextBirthDayAfter(after), is(LocalDate.of(2000, Month.NOVEMBER, 16)));
	}

	@Test
	@DisplayName("should give next year birthday if given year birthday has been passed")
	void test2() {
		final Person person = aPersonBornAt(LocalDate.of(1989, Month.NOVEMBER, 16));
		final LocalDate after = LocalDate.of(2000, Month.NOVEMBER, 20);

		assertThat(person.nextBirthDayAfter(after), is(LocalDate.of(2001, Month.NOVEMBER, 16)));
	}

	@NotNull
	private Person aPersonBornAt(LocalDate birthDate) {
		final PersonId id = PersonId.from(RandomUtils.nextInt());
		final PersonalName name = PersonalName.from(
				Collections.singletonList(FirstName.from("John")),
				Collections.singletonList(FamilyName.from("Doe")));
		return new Person(id, name, birthDate);
	}

}