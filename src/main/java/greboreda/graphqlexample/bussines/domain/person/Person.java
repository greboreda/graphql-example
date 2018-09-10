package greboreda.graphqlexample.bussines.domain.person;

import greboreda.graphqlexample.bussines.domain.ddd.Entity;
import greboreda.graphqlexample.bussines.domain.ddd.valueobjects.Age;
import greboreda.graphqlexample.bussines.domain.ddd.valueobjects.Name;
import greboreda.graphqlexample.bussines.domain.person.majority.CountriesAgesOfMajority;
import greboreda.graphqlexample.bussines.domain.person.majority.PersonAgeOfMajorityDecisor;

import java.time.LocalDate;

public class Person implements Entity<PersonId> {

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

	public PersonAgeOfMajorityDecisor ageOfMajority(CountriesAgesOfMajority countriesAgesOfMajority) {
		return new PersonAgeOfMajorityDecisor(this, countriesAgesOfMajority);
	}

	public LocalDate nextBirthDayAfter(LocalDate after) {
		final LocalDate currentYearBirthday = birthDate.withYear(after.getYear());
		if(currentYearBirthday.isAfter(after)) {
			return currentYearBirthday;
		}
		return birthDate.withYear(after.getYear() + 1);
	}

}
