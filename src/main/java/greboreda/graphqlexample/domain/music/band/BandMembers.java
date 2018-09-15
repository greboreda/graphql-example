package greboreda.graphqlexample.domain.music.band;

import greboreda.graphqlexample.domain.music.musician.Musician;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Stream;

public class BandMembers implements Iterable<Musician> {

	private final Collection<Musician> members;

	private BandMembers(Collection<Musician> members) {
		if(members == null || members.isEmpty()) {
			throw new IllegalArgumentException("must have members");
		}
		this.members = members;
	}

	@NotNull
	@Override
	public Iterator<Musician> iterator() {
		return members.iterator();
	}

	public static BandMembersBuilder create() {
		return new BandMembersBuilder();
	}

	public static class BandMembersBuilder {
		private final Collection<Musician> members;
		public BandMembersBuilder withMember(Musician member) {
			this.members.add(member);
			return this;
		}
		public BandMembers build() {
			return new BandMembers(members);
		}
		private BandMembersBuilder() {
			this.members = new HashSet<>();
		}
	}

	public Stream<Musician> stream() {
		return members.stream();
	}

}
