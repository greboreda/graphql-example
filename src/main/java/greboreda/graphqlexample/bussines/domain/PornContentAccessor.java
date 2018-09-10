package greboreda.graphqlexample.bussines.domain;

import greboreda.graphqlexample.bussines.domain.person.majority.CountriesAgesOfMajority;
import greboreda.graphqlexample.bussines.domain.person.majority.CountriesAgesOfMajorityFinder;
import greboreda.graphqlexample.bussines.domain.person.majority.PersonAgeOfMajorityDecisor;
import greboreda.graphqlexample.bussines.domain.person.majority.CountryAgeOfMajority;
import greboreda.graphqlexample.bussines.domain.person.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

public class PornContentAccessor {

	private final CountriesAgesOfMajorityFinder countriesAgesOfMajorityFinder = new CountriesAgesOfMajorityFinder();

	public PornContentAccess retrieveAccess(Person person, Country country) {

		final CountriesAgesOfMajority countriesAgesOfMajority = countriesAgesOfMajorityFinder.findFor(country);
		final PersonAgeOfMajorityDecisor ageOfMajority = person.ageOfMajority(countriesAgesOfMajority);

		final boolean isMajor = ageOfMajority.hasAgeOfMajorityIn(country).at(LocalDate.now());

		final LocalDate whenCanAccess = ageOfMajority.whenHasMajorityAgeIn(Country.SPAIN);

		if(isMajor) {
			return new PornContentAccess(true, whenCanAccess);
		} else {
			return new PornContentAccess(false, whenCanAccess);
		}
	}

	public static class PornContentAccess {
		public final boolean hasAccess;
		public final LocalDate allowAccessDate;
		public PornContentAccess(boolean hasAccess, LocalDate allowAccessDate) {
			this.hasAccess = hasAccess;
			this.allowAccessDate = allowAccessDate;
		}
	}



}
