package com.ridango.game.data.service;

import com.ridango.game.data.entities.Drink;
import com.ridango.game.data.entities.Ingredient;
import com.ridango.game.data.repository.DrinkRepository;
import com.ridango.game.data.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final ApiService apiService;
    private final IngredientService ingredientService;
    private final DrinkRepository drinkRepository;
    private final IngredientRepository ingredientRepository;

    public void populateDatabase() {
        try {
            List<JsonNode> allDrinks = apiService.fetchDrinksFromApiAllAlphabet();
            for (JsonNode response : allDrinks) {
                JsonNode drinks = response.get("drinks");
                if (drinks != null) {
                    processDrinks(drinks);
                }
            }
        } catch (Exception e) {
            System.out.printf("Error while populating database: %s", e);
        }
    }

    private void processDrinks(JsonNode drinks) {
        for (JsonNode drinkNode : drinks) {
            Drink drink = mapDrink(drinkNode);
            Drink savedDrink = drinkRepository.save(drink);

            List<Ingredient> ingredients = extractIngredients(drinkNode, savedDrink);
            if (!ingredients.isEmpty()) {
                ingredientRepository.saveAll(ingredients);
            }
        }
    }

    private Drink mapDrink(JsonNode drinkNode) {
        return new Drink(
                drinkNode.get("idDrink").asLong(),
                drinkNode.get("strDrink").asText(),
                drinkNode.get("strGlass").asText(),
                drinkNode.get("strInstructions").asText(),
                drinkNode.get("strDrinkThumb").asText() + "/preview"
        );
    }

    private List<Ingredient> extractIngredients(JsonNode drinkNode, Drink savedDrink) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            String ingredientKey = "strIngredient" + i;
            String measureKey = "strMeasure" + i;

            if (drinkNode.has(ingredientKey) && !drinkNode.get(ingredientKey).isNull()) {
                Ingredient ingredient = new Ingredient(
                        savedDrink.getId(),
                        drinkNode.get(ingredientKey).asText(),
                        drinkNode.get(measureKey).asText()
                );
                ingredients.add(ingredient);
            }
        }
        return ingredients;
    }

    public Drink getRandom() {
        Optional<Drink> drinkOptional = drinkRepository.fetchRandomDrink();
        if (drinkOptional.isPresent()) {
            Drink drink = drinkOptional.get();
            drink.setIngredients(ingredientService.getIngredients(drink.getId()));
            return drink;
        }
        return null;
    }

    public void markGuessed(Long id) {
        drinkRepository.markAsGuessed(id);
    }

    public void cleanGuessed() { drinkRepository.cleanGuessed(); }
}
