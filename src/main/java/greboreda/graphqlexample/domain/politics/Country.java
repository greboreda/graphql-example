package greboreda.graphqlexample.domain.politics;

import greboreda.graphqlexample.business.ddd.ValueObject;

import java.util.Locale;

public enum Country implements ValueObject {

	SPAIN(new Locale("es")),
	USA(new Locale("us"));

	private final Locale locale;

	Country(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}

}
