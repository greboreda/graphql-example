package greboreda.graphqlexample.domain.person.nationality;

import greboreda.graphqlexample.ddd.valueobjects.ValueObject;
import greboreda.graphqlexample.domain.politics.Country;

public class Nationality implements ValueObject {

	private final Country country;

	private Nationality(Country country) {
		this.country = country;
	}

	public static Nationality of(Country country) {
		return new Nationality(country);
	}

	@Override
	public String toString() {
		return country.getLocale().getISO3Country();
	}
}
