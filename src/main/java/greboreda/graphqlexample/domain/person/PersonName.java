package greboreda.graphqlexample.domain.person;

import greboreda.graphqlexample.domain.valueobjects.Name;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonName extends Name {

	private final Name firstName;
	private final Name middleName;
	private final Name familyName;

	private PersonName(Name firstName, Name middleName, Name familyName) {
		super(buildCompleteName(firstName, middleName, familyName));
		this.firstName = firstName;
		this.middleName = middleName;
		this.familyName = familyName;
	}

	public static PersonNameBuilder aPersonName() {
		return new PersonNameBuilder();
	}

	@NotNull
	private static String buildCompleteName(Name firstName, Name middleName, Name familyName) {
		return Stream.of(firstName, middleName, familyName)
				.map(Name::getValue)
				.collect(Collectors.joining(" "));
	}

	public static class PersonNameBuilder {
		@FunctionalInterface
		public interface StepMiddleName {
			StepFamilyName withMiddleName(Name middleName);
		}
		@FunctionalInterface
		public interface StepFamilyName {
			PersonName withFamilyName(Name familyName);
		}
		public StepMiddleName withFirstName(Name firstName) {
			return middleName -> lastName -> new PersonName(firstName, middleName, lastName);
		}
	}
}
