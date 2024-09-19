package com.ridango.game.logic;

import com.ridango.game.data.entities.Drink;
import com.ridango.game.data.entities.Highscore;
import com.ridango.game.data.repository.DrinkRepository;
import com.ridango.game.data.repository.IngredientRepository;
import com.ridango.game.data.service.DrinkService;
import com.ridango.game.data.service.HighscoreService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;

@Getter
@Setter
@Service
public class GameLogic {
    @Getter private int numberOfGuesses = 5;
    @Getter private Drink currentDrink;

    @Getter private String currentDrinkName;

    private final DrinkService drinkService;

    private final HighscoreService highscoreService;

    @Getter private StringBuilder hiddenString;

    @Getter private Highscore highScore = new Highscore("", 0);

    public GameLogic(DrinkService drinkService, HighscoreService highscoreService) {
        this.drinkService = drinkService;
        this.highscoreService = highscoreService;
    }

    public boolean wasCorrect(String guess) {
        return guess.equalsIgnoreCase(currentDrinkName);
    }

    public boolean parseGuess(String guess) {
        if (this.numberOfGuesses == 0) {
            return false;
        }

        if (wasCorrect(guess)) {
            this.highScore.setScore(this.highScore.getScore()+this.numberOfGuesses);
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
        this.currentDrink = this.drinkService.getRandom();
        this.currentDrinkName = this.currentDrink.getStrDrink();
        this.hiddenString = new StringBuilder(this.currentDrinkName.replaceAll("[^\\s]", "_"));
    }

    public void endGame(Boolean restarted) {
        this.drinkService.cleanGuessed();
        if (!restarted) {
            this.highscoreService.postScore(this.highScore);
            purgeAttributes();
        }
    }

    public void setName(String name) {
        this.highScore.setName(name);
    }

    public void purgeAttributes() {
        this.highScore = new Highscore();
        this.numberOfGuesses = 5;
        getCocktail();
    }
}
