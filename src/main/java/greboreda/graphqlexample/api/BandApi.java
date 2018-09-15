package greboreda.graphqlexample.api;

import java.util.Collection;

public class BandApi implements ApiObject {

	public Integer id;
	public String name;
	public Collection<String> styles;
	public Collection<MusicianApi> members;

	public static BandApiBuilder builder() {
		return new BandApiBuilder();
	}

	public static final class BandApiBuilder {

		private final BandApi bandApi;

		private BandApiBuilder() {
			bandApi = new BandApi();
		}

		public static BandApiBuilder aBandApi() {
			return new BandApiBuilder();
		}

		public BandApiBuilder withId(Integer id) {
			this.bandApi.id = id;
			return this;
		}

		public BandApiBuilder withName(String name) {
			this.bandApi.name = name;
			return this;
		}

		public BandApiBuilder withStyles(Collection<String> styles) {
			this.bandApi.styles = styles;
			return this;
		}

		public BandApiBuilder withMembers(Collection<MusicianApi> members) {
			this.bandApi.members = members;
			return this;
		}

		public BandApi build() {
			return bandApi;
		}
	}
}
