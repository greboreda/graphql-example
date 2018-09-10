package greboreda.graphqlexample.bussines.domain;


import greboreda.graphqlexample.bussines.domain.ddd.valueobjects.Name;
import greboreda.graphqlexample.bussines.domain.person.Person;
import greboreda.graphqlexample.bussines.domain.person.PersonId;
import greboreda.graphqlexample.bussines.domain.person.majority.CountriesAgesOfMajority;
import greboreda.graphqlexample.bussines.domain.person.majority.CountryAgeOfMajority;
import greboreda.graphqlexample.bussines.domain.person.majority.PersonAgeOfMajorityDecisor.PersonAgeOfMajorityAtCountry;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;

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
		final LocalDate after = LocalDate.of(2000, Month.NOVEMBER, 1);

		assertThat(person.nextBirthDayAfter(after), is(LocalDate.of(2000, Month.NOVEMBER, 16)));
	}

	@Test
	@DisplayName("should have reached age of majority if has greater age than legal age in country")
	void test3() {

		final Person person = aPersonBornAt(LocalDate.of(2000, Month.JANUARY, 1));
		final CountriesAgesOfMajority countriesAgesOfMajority = CountriesAgesOfMajority.create()
				.with(CountryAgeOfMajority.create().withCountry(Country.SPAIN).withNeededLife(Period.ofYears(18)))
				.with(CountryAgeOfMajority.create().withCountry(Country.USA).withNeededLife(Period.ofYears(21)))
				.build();

		final PersonAgeOfMajorityAtCountry decision = person.ageOfMajority(countriesAgesOfMajority)
				.hasMajority()
				.in(Country.SPAIN)
				.orElseThrow(RuntimeException::new);

		assertAll(
				() -> assertThat(decision.hasAgeOfMajorityAt(LocalDate.of(2018, Month.JANUARY, 1)), is(true)),
				() -> assertThat(decision.getMajorityAgeDate(), is(LocalDate.of(2018, Month.JANUARY, 1)))
		);

	}

	@NotNull
	private Person aPersonBornAt(LocalDate birthDate) {
		final PersonId id = PersonId.from(RandomUtils.nextInt());
		final Name name = Name.from(RandomStringUtils.randomAlphabetic(5));
		return new Person(id, name, birthDate);
	}

}