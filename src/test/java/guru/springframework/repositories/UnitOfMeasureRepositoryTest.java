package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository repo;

    @ParameterizedTest
    @ValueSource(strings = {"Teaspoon", "Tablespoon", "Ounce", "Unit", "Pound"})
    public void testFindExistingByDescription(String description) {
        Optional<UnitOfMeasure> actual = repo.findByDescription(description);
        assertThat(actual.isPresent(), equalTo(true));
        assertThat(actual.get().getDescription(), equalTo(description));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Homyak", "Some", "alot"})
    public void testFindNonExistingByDescription(String description) {
        Optional<UnitOfMeasure> actual = repo.findByDescription(description);
        assertThat(actual.isPresent(), equalTo(false));
    }
}