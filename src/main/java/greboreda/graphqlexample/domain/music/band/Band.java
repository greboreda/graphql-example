package greboreda.graphqlexample.domain.music.band;

import greboreda.graphqlexample.business.ddd.Entity;
import greboreda.graphqlexample.domain.aspects.Nameable;
import greboreda.graphqlexample.domain.valueobjects.Name;

public class Band implements Entity<BandId>, Nameable {

	private final BandId id;
	private final Name name;
	private final BandStyle bandStyle;
	private final BandMembers bandMembers;

	private Band(BandId id, Name name, BandStyle bandStyle, BandMembers bandMembers) {
		this.id = id;
		this.name = name;
		this.bandStyle = bandStyle;
		this.bandMembers = bandMembers;
	}

	@Override
	public BandId getId() {
		return id;
	}

	@Override
	public Name getName() {
		return name;
	}

	public BandStyle getBandStyle() {
		return bandStyle;
	}

	public BandMembers getBandMembers() {
		return bandMembers;
	}

	public static BandBuilder create() {
		return new BandBuilder();
	}

	public static class BandBuilder {
		private BandBuilder() {

		}
		@FunctionalInterface
		public interface StepName {
			StepStyle with(Name name);
		}
		@FunctionalInterface
		public interface StepStyle {
			StepMembers with(BandStyle style);
		}
		@FunctionalInterface
		public interface StepMembers {
			Band with(BandMembers bandMembers);
		}
		public StepName with(BandId id) {
			return name -> style -> members -> new Band(id, name, style, members);
		}
	}

}
