package greboreda.graphqlexample.business.ddd;

import greboreda.graphqlexample.business.ddd.primitves.Primitive;

public class Id<P extends Primitive> implements ValueObject {

	private final P value;

	public Id(P value) {
		this.value = value;
	}

	public P get() {
		return value;
	}

	public boolean matches(Id<P> another) {
		return this.value.equals(another.value);
	}


}
