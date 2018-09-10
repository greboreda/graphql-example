package greboreda.graphqlexample.bussines.domain.ddd.valueobjects;

import greboreda.graphqlexample.bussines.domain.ddd.ValueObject;

import java.time.LocalDate;
import java.time.Period;

public class Age implements ValueObject {

	private final LocalDate start;

	public Age(LocalDate start) {
		this.start = start;
	}

	public int yearsAt(LocalDate date) {
		return Period.between(start, date).getYears();
	}

	public LocalDate whenHas(Period period) {
		return start.plus(period);
	}
}
