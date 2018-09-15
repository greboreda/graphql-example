package greboreda.graphqlexample.business;

import greboreda.graphqlexample.domain.music.band.Band;

import javax.inject.Named;
import java.util.Collection;

@Named
public class BandFinder {

	public Collection<Band> findAllBands() {
		return BandsDummyRepository.findAll();
	}

}
