package greboreda.graphqlexample.business.ddd;

public interface Entity<I extends Id> {
	I getId();
}
