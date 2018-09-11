package greboreda.graphqlexample.bussines.domain.person;

import greboreda.graphqlexample.bussines.domain.ddd.Entity;
import greboreda.graphqlexample.bussines.domain.valueobjects.PersonalName;

import java.time.LocalDate;

public class Person implements Entity<PersonId> {

	private final PersonId id;
	private final PersonalName name;
	private final LocalDate birthDate;

	public Person(PersonId id, PersonalName name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	@Override
	public PersonId getId() {
		return id;
	}

	public PersonalName getName() {
		return name;
	}


	public LocalDate nextBirthDayAfter(LocalDate after) {
		final LocalDate currentYearBirthday = birthDate.withYear(after.getYear());
		if(currentYearBirthday.isAfter(after)) {
			return currentYearBirthday;
		}
		return birthDate.withYear(after.getYear()).plusYears(1);
	}

}
