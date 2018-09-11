package greboreda.graphqlexample.bussines.domain.ddd;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {

	T getValue();

	interface StringValue extends ValueObject<String> {
	}

}
