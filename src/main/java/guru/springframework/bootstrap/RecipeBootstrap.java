package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (! eachUomOptional.isPresent()) {
            throw new RuntimeException("Excepted UOM Not Found");
        }

        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tablespoonOptional.isPresent()) {
            throw new RuntimeException("Excepted UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository
                .findByDescription("Teaspoon");

        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Excepted UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository
                .findByDescription("Dash");

        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Excepted UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository
                .findByDescription("Pint");

        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Excepted UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository
                .findByDescription("Cup");

        if (!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Excepted UOM Not Found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();;
        UnitOfMeasure tableSpoonUom = tablespoonOptional.get();;
        UnitOfMeasure teaspoonUom = teaSpoonUomOptional.get();;
        UnitOfMeasure dashUom = dashUomOptional.get();;
        UnitOfMeasure pintUom = pintUomOptional.get();;
        UnitOfMeasure cupsUom = cupsUomOptional.get();;

        // get categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacmole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("XXXXXXXX");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("recipe notes");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(5), teaspoonUom));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("serrano chiles, stems and seeds remove, minced", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipe.getIngredients().add(new Ingredient("freshly grated black peper", new BigDecimal(2), dashUom));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(5), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        // add to return list
        recipes.add(guacRecipe);

        // yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("tacos recipe directions");
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Taco Notes");
        tacoNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("Dired Oreano", new BigDecimal(5), teaspoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("Sugar", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("Salt", new BigDecimal(2), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("finely grated orange zestr", new BigDecimal(2), dashUom));
        tacosRecipe.getIngredients().add(new Ingredient("ingrsfsd", new BigDecimal(5), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("ingrsfsd", new BigDecimal(5), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("sf", new BigDecimal(5), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("ingrsfsd", new BigDecimal(5), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);
        recipes.add(tacosRecipe);
        return recipes;
    }
}
