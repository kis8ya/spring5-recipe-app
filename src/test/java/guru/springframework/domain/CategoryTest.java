package guru.springframework.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CategoryTest {

    private Category category;

    private final Long EXPECTED_ID = 77L;
    private final String EXPECTED_NAME = "Fluffy balls";
    private final Recipe EXPECTED_RECIPE = Recipe.builder()
            .id(123L)
            .description("Chicken").build();
    private final Set<Recipe> EXPECTED_RECIPES = new HashSet<>(Collections.singletonList(EXPECTED_RECIPE));

    @BeforeEach
    public void setUp() {
        category = Category.builder()
                .id(EXPECTED_ID)
                .name(EXPECTED_NAME)
                .recipes(EXPECTED_RECIPES).build();
    }

    @Test
    public void testGetId() {
        assertThat(category.getId(), equalTo(EXPECTED_ID));
    }

    @Test
    public void testTestGetName() {
        assertThat(category.getName(), equalTo(EXPECTED_NAME));
    }

    @Test
    public void testGetRecipes() {
        Set<Recipe> actual = category.getRecipes();
        assertThat(actual, hasSize(1));
        assertThat(actual, equalTo(EXPECTED_RECIPES));
    }
}