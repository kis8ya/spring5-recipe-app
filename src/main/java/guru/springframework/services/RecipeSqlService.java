package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecipeSqlService implements RecipeService{

    private final RecipeRepository repository;

    @Override
    public Set<Recipe> getAll() {
        Set<Recipe> result = new HashSet<>();
        repository.findAll().forEach(result::add);
        log.info("Found " + result.size() + " recipes");
        return result;
    }
}
