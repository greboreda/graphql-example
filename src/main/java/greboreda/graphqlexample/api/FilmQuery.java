package greboreda.graphqlexample.api;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

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
