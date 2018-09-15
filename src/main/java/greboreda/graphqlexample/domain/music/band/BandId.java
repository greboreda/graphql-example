package greboreda.graphqlexample.domain.music.band;

import greboreda.graphqlexample.ddd.valueobjects.Id;
import greboreda.graphqlexample.ddd.valueobjects.primitves.IntValue;

public class BandId extends Id<IntValue> {

	BandId(IntValue value) {
		super(value);
	}

	public static BandId aBandId(Integer value) {
		return new BandId(new IntValue(value));
	}

}
