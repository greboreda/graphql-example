package greboreda.graphqlexample.api.repository;

import greboreda.graphqlexample.api.beans.ActorApi;
import greboreda.graphqlexample.api.beans.FilmApi;
import greboreda.graphqlexample.api.beans.ReviewApi;

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
		jeff.firstName = "Jeff";
		jeff.lastName = "Bridges";
		final ActorApi john = new ActorApi();
		john.name = "John Goodman";
		john.firstName = "John";
		john.lastName = "Goodman";
		final ActorApi steve = new ActorApi();
		steve.name = "Steve Buscemi";
		steve.firstName = "Steve";
		steve.lastName = "Buscemi";


		bigLebowski.starring = Arrays.asList(jeff, john, steve);

		films.add(bigLebowski);
	}

	public Optional<FilmApi> findFilmById(Integer filmId) {
		return films.stream().filter(f -> f.id.equals(filmId)).findFirst();
	}

	public FilmApi appendReview(Integer filmId, ReviewApi reviewApi) {
		final Optional<FilmApi> maybeFilm = findFilmById(filmId);
		if(!maybeFilm.isPresent()) {
			throw new IllegalArgumentException("not found");
		}
		final FilmApi film = maybeFilm.get();
		film.reviews.add(reviewApi);
		return film;
	}
}
