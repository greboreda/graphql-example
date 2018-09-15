package greboreda.graphqlexample.ddd.valueobjects;

import greboreda.graphqlexample.ddd.valueobjects.primitves.Primitive;

public class Id<P extends Primitive> implements ValueObject {

	private final P value;

	public Id(P value) {
		this.value = value;
	}

	public P get() {
		return value;
	}

	public boolean matches(P another) {
		return this.value.equals(another);
	}


}
