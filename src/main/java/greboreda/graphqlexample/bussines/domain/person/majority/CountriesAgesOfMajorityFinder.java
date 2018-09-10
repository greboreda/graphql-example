package greboreda.graphqlexample.bussines.domain.person.majority;

import greboreda.graphqlexample.bussines.domain.Country;
import greboreda.graphqlexample.bussines.domain.person.majority.CountriesAgesOfMajority.CountriesAgesOfMajorityBuilder;

import java.time.Period;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class CountriesAgesOfMajorityFinder {

	private static final CountriesAgesOfMajority countriesAgesOfMajority = CountriesAgesOfMajority.create()
			.with(CountryAgeOfMajority.create().withCountry(Country.SPAIN).withLife(Period.ofYears(18)))
			.with(CountryAgeOfMajority.create().withCountry(Country.USA).withLife(Period.ofYears(21)))
			.build();


	public CountriesAgesOfMajority findFor(Country ...countries) {
		final Set<Country> queryCountries = new HashSet<>(Arrays.asList(countries));
		final CountriesAgesOfMajorityBuilder builder = CountriesAgesOfMajority.create();
		countriesAgesOfMajority.stream()
				.filter(cam -> queryCountries.contains(cam.getCountry()))
				.collect(toList())
				.forEach(builder::with);
		return builder.build();
	}

}
