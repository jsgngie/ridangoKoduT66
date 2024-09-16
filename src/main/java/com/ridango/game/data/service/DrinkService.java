package com.ridango.game.data.service;

import com.ridango.game.data.entities.Drink;
import com.ridango.game.data.entities.Ingredient;
import com.ridango.game.data.repository.DrinkRepository;
import com.ridango.game.data.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkService {

    private final ApiService apiService;
    private final DrinkRepository drinkRepository;
    private final IngredientRepository ingredientRepository;

    public DrinkService(ApiService apiService, DrinkRepository drinkRepository, IngredientRepository ingredientRepository) {
        this.apiService = apiService;
        this.drinkRepository = drinkRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public void saveDrinks() {
        JsonNode response = apiService.fetchDrinksFromApiAllAlphabet();
        JsonNode drinks = response.get("drinks");

        if (drinks != null) {
            for (JsonNode drinkNode : drinks) {
                Drink drink = new Drink(drinkNode.get("idDrink").asLong(), drinkNode.get("strDrink").asText(),
                                        drinkNode.get("strGlass").asText(), drinkNode.get("strInstructions").asText(),
                            drinkNode.get("strDrinkThumb").asText() + "/preview");

                Drink savedDrink = drinkRepository.save(drink);

                List<Ingredient> ingredients = new ArrayList<>();
                for (int i = 1; i <= 15; i++) {
                    String ingredientKey = "strIngredient" + i;
                    String measureKey = "strMeasure" + i;

                    if (drinkNode.has(ingredientKey) && !drinkNode.get(ingredientKey).isNull()) {
                        Ingredient ingredient = new Ingredient(savedDrink, drinkNode.get(ingredientKey).asText(),
                                                               drinkNode.get(measureKey).asText());
                        ingredients.add(ingredient);
                    }
                }

                ingredientRepository.saveAll(ingredients);
            }


        }
    }
}
