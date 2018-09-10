package greboreda.graphqlexample.bussines.domain.person;

import greboreda.graphqlexample.bussines.domain.Country;

import java.time.Period;

public class AgeOfMajorityLocated {

	public final Country country;
	public final Period life;

	public AgeOfMajorityLocated(Country country, Period life) {
		this.country = country;
		this.life = life;
	}
}
