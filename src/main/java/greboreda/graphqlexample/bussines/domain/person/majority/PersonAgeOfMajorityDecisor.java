package greboreda.graphqlexample.bussines.domain.person.majority;

import greboreda.graphqlexample.bussines.domain.Country;
import greboreda.graphqlexample.bussines.domain.person.Person;
import greboreda.graphqlexample.bussines.domain.person.PersonId;
import org.apache.commons.lang3.Validate;

import java.time.LocalDate;
import java.util.Optional;

public class PersonAgeOfMajorityDecisor {

	private final CountriesAgesOfMajority countriesAgesOfMajority;
	private final Person person;

	public PersonAgeOfMajorityDecisor(Person person, CountriesAgesOfMajority countriesAgesOfMajority) {
		Validate.notNull(person);
		Validate.notNull(countriesAgesOfMajority);
		this.person = person;
		this.countriesAgesOfMajority = countriesAgesOfMajority;
	}

	public interface HasMajorityIn {
		Optional<PersonAgeOfMajorityAtCountry> in(Country country);
	}

	public HasMajorityIn hasMajority() {
		return country -> {
			final Optional<CountryAgeOfMajority> maybe = countriesAgesOfMajority.stream()
					.filter(cam -> cam.getCountry() == country)
					.findAny();
			if(!maybe.isPresent()) {
				return Optional.empty();
			}
			final CountryAgeOfMajority countryAgeOfMajority = maybe.get();
			final PersonId id = person.getId();
			final LocalDate whenHas = person.getAge().whenHas(countryAgeOfMajority.getNeededLife());
			return Optional.of(new PersonAgeOfMajorityAtCountry(id, country, whenHas));
		};
	}

	public static class PersonAgeOfMajorityAtCountry {

		private final PersonId personId;
		private final Country country;
		private final LocalDate majorityAgeDate;

		private PersonAgeOfMajorityAtCountry(PersonId personId, Country country, LocalDate majorityAgeDate) {
			this.personId = personId;
			this.country = country;
			this.majorityAgeDate = majorityAgeDate;
		}

		public PersonId getPersonId() {
			return personId;
		}

		public Country getCountry() {
			return country;
		}

		public LocalDate getMajorityAgeDate() {
			return majorityAgeDate;
		}

		public boolean hasAgeOfMajorityAt(LocalDate date) {
			return !getMajorityAgeDate().isAfter(date);
		}
	}

}
