package com.ridango.game.logic;

import com.ridango.game.data.entities.Drink;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameLogic {
    private int numberOfGuesses = 0;
    private List<Drink> guessedCocktails = new ArrayList<>();

    public void getRandom() {

    }
}
