package greboreda.graphqlexample.api.person;

import org.apache.commons.collections.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;

@Named
public class PersonDummyRepository {

	private static final Collection<PersonApi> saved = new ArrayList<>();

	@PostConstruct
	public void init() {
		final PersonApi p1 = new PersonApi();
		p1.name = "Fidel Castro";
		p1.birthDate = LocalDate.of(1926, Month.AUGUST, 13);
		saved.add(p1);
	}

	public void save(PersonApi personApi) {
		saved.add(personApi);
	}

	public Collection<PersonApi> findAll() {
		return CollectionUtils.unmodifiableCollection(saved);
	}

}
