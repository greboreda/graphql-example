package greboreda.graphqlexample.business;

import greboreda.graphqlexample.domain.music.band.Band;
import greboreda.graphqlexample.domain.music.band.BandMembers;
import greboreda.graphqlexample.domain.music.band.BandStyle;
import greboreda.graphqlexample.domain.music.genre.MusicGenre;
import greboreda.graphqlexample.domain.music.genre.MusicGenreTag;
import greboreda.graphqlexample.domain.music.musician.Instrument;
import greboreda.graphqlexample.domain.music.musician.Musician;
import greboreda.graphqlexample.domain.person.Person;
import greboreda.graphqlexample.domain.person.nationality.Nationalities;
import greboreda.graphqlexample.domain.person.nationality.Nationality;
import greboreda.graphqlexample.domain.politics.Country;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static greboreda.graphqlexample.domain.music.band.BandId.aBandId;
import static greboreda.graphqlexample.domain.person.PersonId.aPersonId;
import static greboreda.graphqlexample.domain.person.PersonName.aPersonName;
import static greboreda.graphqlexample.domain.valueobjects.Name.aName;

public class BandsDummyRepository {

	private static final Collection<Band> bands = dummyBands();

	public static Collection<Band> findAll() {
		return bands;
	}

	private static Collection<Band> dummyBands() {
		return Collections.singletonList(ratm());
	}

	private static Band ratm() {
		final Musician tom = Musician.create()
				.being(Person.create()
						.withId(aPersonId(UUID.randomUUID()))
						.withName(aPersonName()
								.withFirstName(aName("Thomas"))
								.withMiddleName(aName("Baptist"))
								.withFamilyName(aName("Morello"))
						)
						.withBirthDate(LocalDate.of(1964, Month.MAY, 30))
						.nationalOf(Nationalities.forOne(Nationality.of(Country.USA)))
				)
				.asKnownAs(aName("Tom Morello"))
				.playerOf(Instrument.GUITAR);

		final Musician zack = Musician.create()
				.being(Person.create()
						.withId(aPersonId(UUID.randomUUID()))
						.withName(aPersonName()
								.withFirstName(aName("Zacarías"))
								.withMiddleName(aName("Manuel"))
								.withFamilyName(aName("de la Rocha")))
						.withBirthDate(LocalDate.of(1970, Month.JANUARY, 12))
						.nationalOf(Nationalities.forOne(Nationality.of(Country.USA)))
				)
				.asKnownAs(aName("Zack de la Rocha"))
				.playerOf(Instrument.VOICE);

		final Musician brad = Musician.create()
				.being(Person.create()
						.withId(aPersonId(UUID.randomUUID()))
						.withName(aPersonName()
								.withFirstName(aName("Bradley"))
								.withMiddleName(aName("J."))
								.withFamilyName(aName("Wilk")))
						.withBirthDate(LocalDate.of(1968, Month.SEPTEMBER, 5))
						.nationalOf(Nationalities.forOne(Nationality.of(Country.USA)))
				)
				.asKnownAs(aName("Brad Wilk"))
				.playerOf(Instrument.DRUMS);

		final Musician tim = Musician.create()
				.being(Person.create()
						.withId(aPersonId(UUID.randomUUID()))
						.withName(aPersonName()
								.withFirstName(aName("Timothy"))
								.withMiddleName(aName("Robert"))
								.withFamilyName(aName("Commerford")))
						.withBirthDate(LocalDate.of(1968, Month.FEBRUARY, 22))
						.nationalOf(Nationalities.forOne(Nationality.of(Country.USA)))
				)
				.asKnownAs(aName("Tim Commerford"))
				.playerOf(Instrument.BASS);


		return Band.create()
				.with(aBandId(1))
				.with(aName("Rage Against the Machine"))
				.with(BandStyle.create()
						.withGenre(MusicGenre.METAL)
						.withTag(MusicGenreTag.ALTERNATIVE)
						.and()
						.withGenre(MusicGenre.FUNK)
						.and()
						.withGenre(MusicGenre.RAP)
						.build())
				.with(BandMembers.create()
						.withMember(tom)
						.withMember(zack)
						.withMember(brad)
						.withMember(tim)
						.build());
	}


}
