package greboreda.graphqlexample.bussines.domain.person;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PersonId should")
class PersonIdTest {

	@Test
	@DisplayName("not be null")
	void testNull() {
		assertThrows(NullPointerException.class, () -> PersonId.from(null));
	}

	@DisplayName("not be less or equals zero")
	@ParameterizedTest(name = "and {arguments} is not")
	@ValueSource(ints = {0, -1, -2, Integer.MIN_VALUE})
	void testZeroOrNegative(int value) {
		assertThrows(IllegalArgumentException.class, () -> PersonId.from(value));
	}

	@DisplayName("be positive")
	@ParameterizedTest(name = "and {arguments} is")
	@ValueSource(ints = {1, 2, 123, Integer.MAX_VALUE})
	void testPositive(int value) {
		assertThat(PersonId.from(value).getValue(), is(value));
	}


}