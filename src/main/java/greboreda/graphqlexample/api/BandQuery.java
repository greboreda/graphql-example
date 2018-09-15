package greboreda.graphqlexample.api;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import greboreda.graphqlexample.business.BandFinder;
import greboreda.graphqlexample.ddd.valueobjects.primitves.IntValue;
import greboreda.graphqlexample.domain.music.band.Band;
import greboreda.graphqlexample.domain.music.genre.MusicGenreTag;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Component
public class BandQuery implements GraphQLQueryResolver {

	private final BandFinder bandFinder;

	@Inject
	public BandQuery(BandFinder bandFinder) {
		this.bandFinder = bandFinder;
	}

	public BandApi findBandById(Integer bandId) {

		final Optional<Band> maybeFound = bandFinder.findAllBands().stream()
				.filter(band -> band.getId().matches(new IntValue(bandId)))
				.findAny();

		if(!maybeFound.isPresent()) {
			return null;
		}

		final Band found = maybeFound.get();

		final BandApi bandApi = new BandApi();
		bandApi.id = found.getId().get().getValue();
		bandApi.name = found.getName().getValue();
		bandApi.style = found.getBandStyle().toMap().entrySet().stream()
				.map(e -> e.getKey() + e.getValue()
						.stream()
						.map(MusicGenreTag::name)
						.collect(joining(" ")))
				.collect(toList());
		bandApi.members = found.getBandMembers().stream()
				.map(m -> {
					final MusicianApi mapi = new MusicianApi();
					mapi.completeName = m.getName().getValue();
					mapi.alias = m.aka().getValue();
					mapi.birthDate = m.getBirthDate().toString();
					mapi.instrument = m.plays().name();
					return mapi;
				})
				.collect(toList());

		return bandApi;
	}

}
