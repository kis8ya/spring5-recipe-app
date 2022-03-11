package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAll();

    Optional<Recipe> getById(Long id);
}
