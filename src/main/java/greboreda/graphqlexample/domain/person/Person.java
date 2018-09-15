package greboreda.graphqlexample.domain.person;

import greboreda.graphqlexample.ddd.valueobjects.Entity;
import greboreda.graphqlexample.domain.aspects.Born;
import greboreda.graphqlexample.domain.aspects.Nameable;
import greboreda.graphqlexample.domain.aspects.Nationalizable;
import greboreda.graphqlexample.domain.person.nationality.Nationalities;

import java.time.LocalDate;

public class Person implements Entity<PersonId>, Nameable, Born, Nationalizable {

	private final PersonId id;
	private final PersonName name;
	private final LocalDate birthDate;
	private final Nationalities nationalities;

	public Person(PersonId id, PersonName name, LocalDate birthDate, Nationalities nationalities) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.nationalities = nationalities;
	}

	@Override
	public PersonId getId() {
		return id;
	}

	@Override
	public PersonName getName() {
		return name;
	}

	@Override
	public LocalDate getBirthDate() {
		return birthDate;
	}

	@Override
	public Nationalities getNationalities() {
		return nationalities;
	}

	public static PersonBuilder create() {
		return new PersonBuilder();
	}

	public static class PersonBuilder {
		@FunctionalInterface
		public interface StepName {
			StepBirthDate withName(PersonName personName);
		}
		@FunctionalInterface
		public interface StepBirthDate {
			StepNationalities withBirthDate(LocalDate birthDate);
		}
		@FunctionalInterface
		public interface StepNationalities {
			Person nationalOf(Nationalities nationalities);
		}
		private PersonBuilder() {

		}
		public StepName withId(PersonId personId) {
			return personName -> birthDate -> nationalities  -> new Person(personId, personName, birthDate, nationalities);
		}
	}
}
