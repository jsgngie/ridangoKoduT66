package com.ridango.game.logic;

import com.ridango.game.data.entities.Drink;
import com.ridango.game.data.repository.DrinkRepository;
import com.ridango.game.data.repository.IngredientRepository;
import com.ridango.game.data.service.DrinkService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameLogic {
    @Getter private int numberOfGuesses = 5;
    private Drink currentDrink;

    @Getter private String currentDrinkName;

    private final DrinkService drinkService;

    @Getter private StringBuilder hiddenString;

    @Getter private int highScore;

    public GameLogic(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    public boolean wasCorrect(String guess) {
        return guess.equals(currentDrinkName);
    }

    public boolean parseGuess(String guess) {
        if (wasCorrect(guess)) {
            this.highScore += this.numberOfGuesses;
            this.numberOfGuesses = 5;
            drinkService.markGuessed(this.currentDrink.getId());
            getCocktail();
            return true;
        } else {
            this.numberOfGuesses -= 1;
            revealSomeLetters();
            return false;
        }
    }

    private void revealSomeLetters() {
        int howMany = this.currentDrinkName.length() / 7 + 1;
        Random random = new Random();

        int amountHidden = (int) this.hiddenString.chars().filter(ch -> ch == '_').count();
        int revealed = 0;

        while (revealed != howMany && amountHidden != 0) {
            int randIndex = random.nextInt(this.currentDrinkName.length());

            if (this.hiddenString.charAt(randIndex) == '_') {
                this.hiddenString.setCharAt(randIndex, this.currentDrinkName.charAt(randIndex));
                revealed++;
            }
        }
    }

    public void getCocktail() {
        this.currentDrink = drinkService.getRandom();
        this.currentDrinkName = this.currentDrink.getStrDrink();
        this.hiddenString = new StringBuilder(this.currentDrinkName.replaceAll("[^\\s]", "_"));
    }
}
