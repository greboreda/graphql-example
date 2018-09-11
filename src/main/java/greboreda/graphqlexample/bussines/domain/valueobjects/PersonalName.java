package greboreda.graphqlexample.bussines.domain.valueobjects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonalName implements Name {

	private final Collection<FirstName> firstNames;
	private final Collection<FamilyName> familyNames;

	private PersonalName(Collection<FirstName> firstNames, Collection<FamilyName> familyNames) {
		Validate.notEmpty(firstNames);
		Validate.notEmpty(familyNames);
		this.firstNames = firstNames;
		this.familyNames = familyNames;
	}

	@Override
	public String getValue() {
		return Stream.concat(firstNames.stream(), familyNames.stream())
				.map(Name::getValue)
				.collect(Collectors.joining(StringUtils.SPACE));
	}

	public static PersonalName from(Collection<FirstName> firstNames, Collection<FamilyName> familyNames) {
		return new PersonalName(firstNames, familyNames);
	}


}
