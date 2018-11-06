package greboreda.graphqlexample.api.mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import greboreda.graphqlexample.api.beans.FilmApi;
import greboreda.graphqlexample.api.beans.ReviewApi;
import greboreda.graphqlexample.api.beans.input.ReviewCreationApi;
import greboreda.graphqlexample.api.repository.FilmRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@SuppressWarnings("unused")
public class FilmMutation implements GraphQLMutationResolver {

	private final FilmRepository filmRepository;

	@Inject
	public FilmMutation(final FilmRepository filmRepository) {
		this.filmRepository = filmRepository;
	}

	public FilmApi createReview(Integer filmId, ReviewCreationApi reviewCreation) {
		final ReviewApi review = new ReviewApi();
		review.author = reviewCreation.author;
		review.commentary = reviewCreation.commentary;
		review.stars = reviewCreation.stars.value;
		return filmRepository.appendReview(filmId, review);
	}
}
