package greboreda.graphqlexample.domain.person.nationality;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Nationalities implements Iterable<Nationality> {

	private final Collection<Nationality> nationalities;

	private Nationalities(Collection<Nationality> nationalities) {
		if(nationalities == null || nationalities.isEmpty()) {
			throw new IllegalArgumentException("can not have no nationalities");
		}
		this.nationalities = nationalities;
	}

	public static Nationalities forOne(Nationality nationality) {
		return new Nationalities(Collections.singleton(nationality));
	}

	public static NationalitiesBuilder many() {
		return new NationalitiesBuilder();
	}

	@NotNull
	@Override
	public Iterator<Nationality> iterator() {
		return nationalities.iterator();
	}

	public static class NationalitiesBuilder {
		private final Collection<Nationality> nationalities;
		private NationalitiesBuilder() {
			nationalities = new HashSet<>();
		}
		public NationalitiesBuilder with(Nationality nationality) {
			nationalities.add(nationality);
			return this;
		}
		public Nationalities andNoMore() {
			return new Nationalities(nationalities);
		}

	}

}
