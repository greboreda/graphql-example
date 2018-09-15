package greboreda.graphqlexample.api;

public class MusicianApi implements ApiObject {

	public String completeName;
	public String alias;
	public String birthDate;
	public String instrument;

	public static MusicianApiBuilder builder() {
		return new MusicianApiBuilder();
	}

	public static final class MusicianApiBuilder {
		
		private final MusicianApi musicianApi;

		private MusicianApiBuilder() {
			musicianApi = new MusicianApi();
		}

		public static MusicianApiBuilder aMusicianApi() {
			return new MusicianApiBuilder();
		}

		public MusicianApiBuilder withCompleteName(String completeName) {
			this.musicianApi.completeName = completeName;
			return this;
		}

		public MusicianApiBuilder withAlias(String alias) {
			this.musicianApi.alias = alias;
			return this;
		}

		public MusicianApiBuilder withBirthDate(String birthDate) {
			this.musicianApi.birthDate = birthDate;
			return this;
		}

		public MusicianApiBuilder withInstrument(String instrument) {
			this.musicianApi.instrument = instrument;
			return this;
		}

		public MusicianApi build() {
			return musicianApi;
		}
	}
}
