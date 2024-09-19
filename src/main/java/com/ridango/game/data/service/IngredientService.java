package com.ridango.game.data.service;

import com.ridango.game.data.entities.Ingredient;
import com.ridango.game.data.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getIngredients(Long id) {
        return ingredientRepository.findByDrinkId(id);
    }
}
