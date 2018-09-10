package greboreda.graphqlexample.bussines.domain.person.majority;

import greboreda.graphqlexample.bussines.domain.Country;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CountriesAgesOfMajority {

	private final Set<CountryAgeOfMajority> collection;

	private CountriesAgesOfMajority() {
		this.collection = new HashSet<>();
	}

	public Stream<CountryAgeOfMajority> stream() {
		return collection.stream();
	}

	public static CountriesAgesOfMajorityBuilder create() {
		return new CountriesAgesOfMajorityBuilder();
	}

	public static class CountriesAgesOfMajorityBuilder {
		private final CountriesAgesOfMajority countriesAgesOfMajority;
		private CountriesAgesOfMajorityBuilder() {
			countriesAgesOfMajority = new CountriesAgesOfMajority();
		}
		public CountriesAgesOfMajorityBuilder with(CountryAgeOfMajority countryAgeOfMajority) {
			validateCountryIsNotYetPresent(countryAgeOfMajority);
			countriesAgesOfMajority.collection.add(countryAgeOfMajority);
			return this;
		}

		private void validateCountryIsNotYetPresent(CountryAgeOfMajority countryAgeOfMajority) {
			countriesAgesOfMajority.collection.stream()
					.filter(cam -> cam.getCountry() == countryAgeOfMajority.getCountry())
					.findAny()
					.ifPresent(cam -> throwRepeatedCountryError(cam.getCountry()));
		}

		public CountriesAgesOfMajority build() {
			return countriesAgesOfMajority;
		}

		private void throwRepeatedCountryError(Country repeated) {
			throw new IllegalArgumentException("repeated country: " + repeated);
		}
	}

}
