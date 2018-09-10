package greboreda.graphqlexample.infrasctructure;

import java.time.LocalDate;

public class TimeFactory {

	public LocalDate currentLocalDate() {
		return LocalDate.now();
	}

}
