package greboreda.graphqlexample.domain.music.musician;

import greboreda.graphqlexample.business.ddd.Entity;
import greboreda.graphqlexample.domain.aspects.Born;
import greboreda.graphqlexample.domain.aspects.Nameable;
import greboreda.graphqlexample.domain.aspects.Nationalizable;
import greboreda.graphqlexample.domain.person.Person;
import greboreda.graphqlexample.domain.person.PersonId;
import greboreda.graphqlexample.domain.person.nationality.Nationalities;
import greboreda.graphqlexample.domain.valueobjects.Name;

import java.time.LocalDate;

public class Musician implements Entity<PersonId>, Nameable, Born, Nationalizable {

	private final Person person;
	private final Name alias;
	private final Instrument instrument;

	private Musician(Person person, Name alias, Instrument instrument) {
		this.person = person;
		this.alias = alias;
		this.instrument = instrument;
	}

	@Override
	public PersonId getId() {
		return person.getId();
	}

	public Instrument plays() {
		return instrument;
	}

	public Name aka() {
		return alias;
	}

	@Override
	public LocalDate getBirthDate() {
		return person.getBirthDate();
	}

	@Override
	public Name getName() {
		return person.getName();
	}

	@Override
	public Nationalities getNationalities() {
		return person.getNationalities();
	}

	public static MusicianBuilder create() {
		return new MusicianBuilder();
	}

	public static class MusicianBuilder {
		private MusicianBuilder() {
		}
		@FunctionalInterface
		public interface StepAlias {
			StepInstrument asKnownAs(Name alias);
		}
		@FunctionalInterface
		public interface StepInstrument {
			Musician playerOf(Instrument instrument);
		}
		public StepAlias being(Person person) {
			return alias -> instrument -> new Musician(person, alias, instrument);
		}
	}

}
