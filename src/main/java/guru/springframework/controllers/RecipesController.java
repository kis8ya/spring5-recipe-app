package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping({"/recipes"})
public class RecipesController {

    private final RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public String getRecipe(Model model, @PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.getById(id);

        String template = "errors/not-found";
        System.out.println(recipe.isPresent());
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            template = "recipes/get";
        }
        return template;
    }


}
