package greboreda.graphqlexample.api;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import greboreda.graphqlexample.business.BandFinder;
import greboreda.graphqlexample.domain.music.band.BandId;
import greboreda.graphqlexample.domain.music.genre.MusicGenreTag;

import javax.inject.Inject;
import javax.inject.Named;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Named
@SuppressWarnings("unused")
public class BandQuery implements GraphQLQueryResolver {

	private final BandFinder bandFinder;

	@Inject
	public BandQuery(BandFinder bandFinder) {
		this.bandFinder = bandFinder;
	}

	public BandApi findBandById(Integer bandId) {
		return bandFinder.findAllBands().stream()
				.filter(band -> band.getId().matches(BandId.aBandId(bandId)))
				.findAny()
				.map(found -> BandApi.builder()
						.withId(found.getId().get().getValue())
						.withName(found.getName().getValue())
						.withStyles(found.getBandStyle().toMap().entrySet().stream()
								.map(e -> e.getKey() + " " + e.getValue()
										.stream()
										.map(MusicGenreTag::name)
										.collect(joining(" ")))
								.collect(toList()))
						.withMembers(found.getBandMembers().stream()
								.map(m -> MusicianApi.builder()
										.withCompleteName(m.getName().getValue())
										.withAlias(m.aka().getValue())
										.withBirthDate(m.getBirthDate().toString())
										.withInstrument(m.plays().name())
										.build())
								.collect(toList()))
						.build())
				.orElse(null);
	}

}
