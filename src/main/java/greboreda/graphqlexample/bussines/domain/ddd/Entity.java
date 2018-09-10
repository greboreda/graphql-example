package greboreda.graphqlexample.bussines.domain.ddd;

public interface Entity<T extends Id> {
	T getId();
}
