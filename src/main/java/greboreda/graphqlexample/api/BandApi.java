package greboreda.graphqlexample.api;

import java.util.Collection;

public class BandApi implements ApiObject {

	public Integer id;
	public String name;
	public Collection<String> style;
	public Collection<MusicianApi> members;

}
