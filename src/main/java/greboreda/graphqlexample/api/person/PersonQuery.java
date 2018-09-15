package greboreda.graphqlexample.api.person;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named
public class PersonQuery implements GraphQLQueryResolver {

	private final PersonDummyRepository personDummyRepository;

	@Inject
	public PersonQuery(PersonDummyRepository personDummyRepository) {
		this.personDummyRepository = personDummyRepository;
	}

	public Collection<PersonApi> findAllPersons() {
		return personDummyRepository.findAll();
	}

}
