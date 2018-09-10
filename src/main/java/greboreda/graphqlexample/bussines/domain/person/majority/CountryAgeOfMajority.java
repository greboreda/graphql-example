package greboreda.graphqlexample.bussines.domain.person.majority;

import greboreda.graphqlexample.bussines.domain.Country;

import java.time.Period;

public class CountryAgeOfMajority {

	private final Country country;
	private final Period life;

	private CountryAgeOfMajority(Country country, Period life) {
		this.country = country;
		this.life = life;
	}

	public Country getCountry() {
		return country;
	}

	public Period getLife() {
		return life;
	}

	public static CountryAgeOfMajorityBuilder create() {
		return new CountryAgeOfMajorityBuilder();
	}

	public static class CountryAgeOfMajorityBuilder {
		@FunctionalInterface
		public interface StepLife {
			CountryAgeOfMajority withLife(Period life);
		}
		private CountryAgeOfMajorityBuilder() {

		}
		public StepLife withCountry(Country country) {
			return life -> new CountryAgeOfMajority(country, life);
		}
	}
}
