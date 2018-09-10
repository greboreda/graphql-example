package greboreda.graphqlexample.bussines.domain.person;

import greboreda.graphqlexample.bussines.domain.ddd.Entity;
import greboreda.graphqlexample.bussines.domain.ddd.valueobjects.Age;
import greboreda.graphqlexample.bussines.domain.ddd.valueobjects.Name;

import java.time.LocalDate;
import java.util.List;

public class Person implements Entity<PersonId> {

	//private static final int MAJORITY_AGE_NEEDED_YEARS = 18;

	private final PersonId id;
	private final Name name;
	private final LocalDate birthDate;

	public Person(PersonId id, Name name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	@Override
	public PersonId getId() {
		return id;
	}

	public Name getName() {
		return name;
	}

	public Age getAge() {
		return new Age(birthDate);
	}

/*
	public LocalDate majorityAge() {
		return getAge().whenHas(Period.ofYears(MAJORITY_AGE_NEEDED_YEARS));
	}

	public boolean hasReachedAgeOfMajorityAt(LocalDate date) {
		final LocalDate majorityAge = majorityAge();
		return majorityAge.isEqual(date) || majorityAge.isAfter(date);
	}
*/

	public AgeOfMajority ageOfMajority(List<AgeOfMajorityLocated> agesOfMajorityLocated) {
		return new AgeOfMajority(this, agesOfMajorityLocated);
	}

	public LocalDate nextBirthDayAfter(LocalDate after) {
		final LocalDate currentYearBirthday = birthDate.withYear(after.getYear());
		if(currentYearBirthday.isAfter(after)) {
			return currentYearBirthday;
		}
		return birthDate.withYear(after.getYear() + 1);
	}

}
