package greboreda.graphqlexample.api.beans.input;

public class ReviewCreationApi {

	public String author;
	public String commentary;
	public ReviewStarsApi stars;

	public enum ReviewStarsApi {
		ONE(1),
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5);

		public final int value;

		ReviewStarsApi(final int value) {
			this.value = value;
		}

	}

}
