package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(
            RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository,
            UnitOfMeasureRepository unitOfMeasureRepository,
            CategoryRepository categoryRepository
    ) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<UnitOfMeasure> unitO = unitOfMeasureRepository.findByDescription("Unit");
        UnitOfMeasure unit;
        if (unitO.isPresent()) {
            unit = unitO.get();
        } else {
            throw new Exception();
        }
        Optional<UnitOfMeasure> ounceO = unitOfMeasureRepository.findByDescription("Ounce");
        UnitOfMeasure ounce;
        if (ounceO.isPresent()) {
            ounce = ounceO.get();
        } else {
            throw new Exception();
        }
        Optional<UnitOfMeasure> poundO = unitOfMeasureRepository.findByDescription("Pound");
        UnitOfMeasure pound;
        if (poundO.isPresent()) {
            pound = poundO.get();
        } else {
            throw new Exception();
        }
        Optional<UnitOfMeasure> teaspoonO = unitOfMeasureRepository.findByDescription("Teaspoon");
        UnitOfMeasure teaspoon;
        if (teaspoonO.isPresent()) {
            teaspoon = teaspoonO.get();
        } else {
            throw new Exception();
        }
        Optional<UnitOfMeasure> tablespoonO = unitOfMeasureRepository.findByDescription("Tablespoon");
        UnitOfMeasure tablespoon;
        if (tablespoonO.isPresent()) {
            tablespoon = tablespoonO.get();
        } else {
            throw new Exception();
        }

        Recipe guacamole = new Recipe();
        guacamole.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chilis, cilantro, and some chopped tomato.");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(1);
        guacamole.setServings(4);
        guacamole.setSource("Random girl");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("1. Cut the avocado:" +
                "\n2. Mash the avocado flesh:" +
                "\n3. Add remaining ingredients to taste:" +
                "\n4. Serve immediately:");
        Set<Ingredient> guacamoleIngredients = new HashSet<>();
        guacamoleIngredients.add(new Ingredient("ripe avocados", BigDecimal.valueOf(2), unit));
        guacamoleIngredients.add(new Ingredient("salt", BigDecimal.valueOf(0.25), teaspoon));
        guacamoleIngredients.add(new Ingredient("fresh lime", BigDecimal.valueOf(1), tablespoon));
        guacamole.setIngredients(guacamoleIngredients);
        guacamole.setDifficulty(Difficulty.EASY);
        Set<Category> categories = new HashSet<>();
        System.out.println(categoryRepository.findAll());
        categories.add(categoryRepository.findByName("Mexican").get());
        categories.add(categoryRepository.findByName("American").get());
        guacamole.setCategories(categories);
        recipeRepository.save(guacamole);

        Recipe roastChicken = new Recipe();
        roastChicken.setDescription("If you've never had Feta-Brined Roast Chicken before, you are in for the surprise of your life, because it's THAT GOOD.");
        roastChicken.setPrepTime(15);
        roastChicken.setCookTime(50);
        roastChicken.setServings(4);
        roastChicken.setSource("Random man");
        roastChicken.setUrl("https://www.simplyrecipes.com/recipes/feta_brined_roast_chicken/");
        roastChicken.setDirections("1. Make the feta brine" +
                "\n2. Brine the chicken" +
                "\n3. Dry the chicken, and bring it to room temperature" +
                "\n4. Preheat the oven to 450°F" +
                "\n5. Roast the chicken");
        Set<Ingredient> chickenIngredients = new HashSet<>();
        chickenIngredients.add(new Ingredient("feta cheese", BigDecimal.valueOf(2), ounce));
        chickenIngredients.add(new Ingredient("salt", BigDecimal.valueOf(2), teaspoon));
        chickenIngredients.add(new Ingredient("chicken", BigDecimal.valueOf(3.5), pound));
        roastChicken.setIngredients(chickenIngredients);
        roastChicken.setDifficulty(Difficulty.EASY);
        Set<Category> chickenCategories = new HashSet<>();
        chickenCategories.add(categoryRepository.findByName("American").get());
        roastChicken.setCategories(chickenCategories);
        recipeRepository.save(roastChicken);


    }
}
