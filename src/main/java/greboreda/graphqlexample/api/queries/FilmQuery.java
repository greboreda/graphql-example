package greboreda.graphqlexample.api.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import greboreda.graphqlexample.api.repository.FilmRepository;
import greboreda.graphqlexample.api.beans.FilmApi;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@SuppressWarnings("unused")
public class FilmQuery implements GraphQLQueryResolver {

	private final FilmRepository filmRepository;

	@Inject
	public FilmQuery(FilmRepository filmRepository) {
		this.filmRepository = filmRepository;
	}

	public FilmApi findFilmById(Integer filmId) {
		return filmRepository.findFilmById(filmId).orElse(null);
	}

}
