package greboreda.graphqlexample.bussines.domain;

import greboreda.graphqlexample.bussines.domain.person.AgeOfMajority;
import greboreda.graphqlexample.bussines.domain.person.AgeOfMajorityLocated;
import greboreda.graphqlexample.bussines.domain.person.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

public class PornContentAccessor {

	public PornContentAccess retrieveAccess(Person person) {

		final AgeOfMajority ageOfMajority = person.ageOfMajority(findLocs());
		final boolean isMajor = ageOfMajority.hasAgeOfMajorityIn(Country.SPAIN).at(LocalDate.now());
		final LocalDate whenCanAccess = ageOfMajority.whenHasMajorityAgeIn(Country.SPAIN);

		if(isMajor) {
			return new PornContentAccess(true, whenCanAccess);
		} else {
			return new PornContentAccess(false, whenCanAccess);
		}
	}

	private List<AgeOfMajorityLocated> findLocs() {
		return Arrays.asList(
				new AgeOfMajorityLocated(Country.SPAIN, Period.ofYears(18)),
				new AgeOfMajorityLocated(Country.USA, Period.ofYears(21))
		);
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
