package greboreda.graphqlexample.bussines.domain;

import greboreda.graphqlexample.bussines.domain.PornContentAccessor.PornContentAccess;
import greboreda.graphqlexample.bussines.domain.ddd.valueobjects.Name;
import greboreda.graphqlexample.bussines.domain.person.Person;
import greboreda.graphqlexample.bussines.domain.person.PersonId;
import greboreda.graphqlexample.bussines.domain.person.majority.CountriesAgesOfMajority;
import greboreda.graphqlexample.bussines.domain.person.majority.CountriesAgesOfMajorityFinder;
import greboreda.graphqlexample.bussines.domain.person.majority.CountryAgeOfMajority;
import greboreda.graphqlexample.infrasctructure.TimeFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PornContentAccessorTest {

	private TimeFactory timeFactory;
	private CountriesAgesOfMajorityFinder countriesAgesOfMajorityFinder;
	private PornContentAccessor pornContentAccessor;

	@BeforeEach
	void setUp() {
		timeFactory = mock(TimeFactory.class);
		countriesAgesOfMajorityFinder = mock(CountriesAgesOfMajorityFinder.class);
		pornContentAccessor = new PornContentAccessor(timeFactory, countriesAgesOfMajorityFinder);
	}

	@Test
	@DisplayName("should have access if has reached majority age")
	void test1() {

		final ZonedDateTime madrid = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));
		final ZonedDateTime canarias = ZonedDateTime.now(ZoneId.of("Europe/Lisbon"));

		assertThat(canarias.isAfter(madrid), is(true));
	}

	@Test
	void foo() {

		final Country spain = Country.SPAIN;

		final Person person = aPersonBornAt(LocalDate.of(2000, Month.JANUARY, 1));

		when(timeFactory.currentLocalDate()).thenReturn(LocalDate.of(2018, Month.SEPTEMBER, 10));
		when(countriesAgesOfMajorityFinder.findFor(spain)).thenReturn(
				CountriesAgesOfMajority.create()
						.with(CountryAgeOfMajority.create()
								.withCountry(Country.SPAIN)
								.withNeededLife(Period.ofYears(18)))
						.build()
		);

		final PornContentAccess access = pornContentAccessor.retrieveAccess(person, spain);

		assertThat(access.hasAccess, is(true));
	}

	private Person aPersonBornAt(LocalDate birthDate) {
		final PersonId personId = PersonId.from(RandomUtils.nextInt());
		final Name name = Name.from(RandomStringUtils.random(6));
		return new Person(personId, name, birthDate);
	}

}