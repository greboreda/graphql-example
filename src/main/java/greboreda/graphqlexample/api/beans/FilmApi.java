package greboreda.graphqlexample.api.beans;

import java.util.ArrayList;
import java.util.Collection;

public class FilmApi {

	public Integer id;
	public String title;
	public Integer year;
	public Collection<ActorApi> starring = new ArrayList<>();
	public Collection<ReviewApi> reviews = new ArrayList<>();

}
