package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RecipeSqlServiceTest {

    private RecipeSqlService service;

    private final Recipe EXPECTED_FIRST_RECIPE = Recipe.builder().id(35L).build();
    private final Recipe EXPECTED_SECOND_RECIPE = Recipe.builder().id(351L).build();

    @Mock
    RecipeRepository repo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(repo.findAll())
                .thenReturn(Arrays.asList(EXPECTED_FIRST_RECIPE, EXPECTED_SECOND_RECIPE));

        service = new RecipeSqlService(repo);
    }

    @Test
    public void testGetAll() {
        List<Recipe> actual = new ArrayList<>(service.getAll());

        assertThat(actual, hasSize(2));
        assertThat(actual, hasItems(EXPECTED_FIRST_RECIPE, EXPECTED_SECOND_RECIPE));

        Mockito.verify(repo, Mockito.times(1)).findAll();
    }
}