package greboreda.graphqlexample.ddd.valueobjects;

public interface Entity<I extends Id> {
	I getId();
}
