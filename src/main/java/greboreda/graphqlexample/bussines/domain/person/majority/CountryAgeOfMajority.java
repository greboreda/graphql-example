package greboreda.graphqlexample.bussines.domain.person.majority;

import greboreda.graphqlexample.bussines.domain.Country;

import java.time.Period;

public class CountryAgeOfMajority {

	private final Country country;
	private final Period neededLife;

	private CountryAgeOfMajority(Country country, Period needeLife) {
		this.country = country;
		this.neededLife = needeLife;
	}

	public Country getCountry() {
		return country;
	}

	public Period getNeededLife() {
		return neededLife;
	}

	public static CountryAgeOfMajorityBuilder create() {
		return new CountryAgeOfMajorityBuilder();
	}

	public static class CountryAgeOfMajorityBuilder {
		@FunctionalInterface
		public interface StepNeededLife {
			CountryAgeOfMajority withNeededLife(Period neededLife);
		}
		private CountryAgeOfMajorityBuilder() {

		}
		public StepNeededLife withCountry(Country country) {
			return neededLife -> new CountryAgeOfMajority(country, neededLife);
		}
	}
}
