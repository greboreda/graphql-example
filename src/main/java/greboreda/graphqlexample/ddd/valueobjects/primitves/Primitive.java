package greboreda.graphqlexample.ddd.valueobjects.primitves;

public abstract class Primitive<V> {

	private final V value;

	public Primitive(V value) {
		this.value = value;
	}

	public V getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Primitive<?> primitive = (Primitive<?>) o;

		return value != null ? value.equals(primitive.value) : primitive.value == null;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}
}
