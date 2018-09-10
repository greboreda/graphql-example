package greboreda.graphqlexample.bussines.domain.person.majority;

import greboreda.graphqlexample.bussines.domain.Country;
import greboreda.graphqlexample.bussines.domain.person.Person;
import org.apache.commons.lang3.Validate;

import java.time.LocalDate;
import java.time.Period;
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

	public interface HasMajority {
		Optional<Boolean> at(LocalDate date);
	}

	public HasMajority hasAgeOfMajorityIn(Country country) {
		return date -> whenHasMajorityAgeIn(country).map(d -> !d.isAfter(date));
	}

	public Optional<LocalDate> whenHasMajorityAgeIn(Country country) {
		return majorityLifeAt(country)
				.map(l -> person.getAge().whenHas(l));
	}

	private Optional<Period> majorityLifeAt(Country country) {
		return countriesAgesOfMajority.stream()
				.filter(a -> a.getCountry() == country)
				.map(CountryAgeOfMajority::getLife)
				.findAny();
	}

}
