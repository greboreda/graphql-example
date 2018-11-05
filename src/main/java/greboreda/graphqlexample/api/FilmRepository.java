package greboreda.graphqlexample.api;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Named
public class FilmRepository {

	private static final Collection<FilmApi> films = new ArrayList<>();
	static {

		final FilmApi bigLebowski = new FilmApi();
		bigLebowski.id = 1;
		bigLebowski.title = "The Big Lebowski";
		bigLebowski.year = 1998;

		final ActorApi jeff = new ActorApi();
		jeff.name = "Jeff Bridges";
		final ActorApi john = new ActorApi();
		john.name = "John Goodman";
		final ActorApi steve = new ActorApi();
		steve.name = "Steve Buscemi";

		bigLebowski.starring = Arrays.asList(jeff, john, steve);

		films.add(bigLebowski);
	}

	public Optional<FilmApi> findFilmById(Integer filmId) {
		return films.stream().filter(f -> f.id.equals(filmId)).findFirst();
	}

}
