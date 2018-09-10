package greboreda.graphqlexample.bussines.domain.person;

import greboreda.graphqlexample.bussines.domain.Country;
import org.apache.commons.lang3.Validate;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AgeOfMajority {

	private final List<AgeOfMajorityLocated> agesOfMajorityLocated;
	private final Person person;

	AgeOfMajority(Person person, List<AgeOfMajorityLocated> agesOfMajorityLocated) {
		Validate.notNull(person);
		validate(agesOfMajorityLocated);
		this.person = person;
		this.agesOfMajorityLocated = agesOfMajorityLocated;
	}

	public HasMajority hasAgeOfMajorityIn(Country country) {
		return date -> {
			final LocalDate when = whenHasMajorityAgeIn(country);
			return when.isEqual(date) || when.isBefore(date);
		};
	}

	public interface HasMajority {
		boolean at(LocalDate date);
	}

	public LocalDate whenHasMajorityAgeIn(Country country) {
		return person.getAge().whenHas(majorityLifeAt(country));
	}

	private Period majorityLifeAt(Country country) {
		return agesOfMajorityLocated.stream()
				.filter(a -> a.country == country)
				.findAny()
				.orElseThrow(() -> new RuntimeException("I dont know"))
				.life;
	}

	private void validate(List<AgeOfMajorityLocated> agesOfMajorityLocated) {
		Validate.notEmpty(agesOfMajorityLocated);
		final Set<Country> countries = agesOfMajorityLocated.stream()
				.map(a -> a.country)
				.collect(Collectors.toSet());
		if(countries.size() != agesOfMajorityLocated.size()) {
			throw new IllegalArgumentException("countries repeated");
		}

	}

}
