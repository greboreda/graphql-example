package greboreda.graphqlexample.bussines.domain;

import greboreda.graphqlexample.bussines.domain.person.Person;
import greboreda.graphqlexample.bussines.domain.person.majority.CountriesAgesOfMajority;
import greboreda.graphqlexample.bussines.domain.person.majority.CountriesAgesOfMajorityFinder;
import greboreda.graphqlexample.bussines.domain.person.majority.PersonAgeOfMajorityDecisor.PersonAgeOfMajorityAtCountry;
import greboreda.graphqlexample.infrasctructure.TimeFactory;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.function.Supplier;

public class PornContentAccessor {

	private final TimeFactory timeFactory;
	private final CountriesAgesOfMajorityFinder countriesAgesOfMajorityFinder;

	public PornContentAccessor(TimeFactory timeFactory, CountriesAgesOfMajorityFinder countriesAgesOfMajorityFinder) {
		this.timeFactory = timeFactory;
		this.countriesAgesOfMajorityFinder = countriesAgesOfMajorityFinder;
	}

	public PornContentAccess retrieveAccess(Person person, Country country) {
		final PersonAgeOfMajorityAtCountry personAgeOfMajorityAtCountry = retrievePersonAgeOfMajorityAtCountry(person, country);
		final boolean hasAccess = personAgeOfMajorityAtCountry.hasAgeOfMajorityAt(now());
		return new PornContentAccess(hasAccess, personAgeOfMajorityAtCountry.getMajorityAgeDate());
	}

	private LocalDate now() {
		return timeFactory.currentLocalDate();
	}

	@NotNull
	private PersonAgeOfMajorityAtCountry retrievePersonAgeOfMajorityAtCountry(Person person, Country country) {
		final CountriesAgesOfMajority countriesAgesOfMajority = countriesAgesOfMajorityFinder.findFor(country);
		return person.ageOfMajority(countriesAgesOfMajority)
				.hasMajority()
				.in(country)
				.orElseThrow(canNotDecideError());
	}

	private Supplier<RuntimeException> canNotDecideError() {
		return () -> new IllegalStateException("can not decide");
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
