package greboreda.graphqlexample.persistence;

public interface Repository<T,ID> {

	T find(ID id);
	T save(T entity);

}
