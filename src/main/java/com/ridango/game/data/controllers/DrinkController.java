package com.ridango.game.data.controllers;

import com.ridango.game.data.service.DrinkService;
import com.ridango.game.logic.GameLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DrinkController {

    private final DrinkService drinkService;

    private final GameLogic gameLogic;

    @GetMapping("/setup")
    public Boolean fetchAndSaveDrinks() {
        drinkService.populateDatabase();
        try {
            gameLogic.getCocktail(); // if no cocktails were found
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @GetMapping("/guess/{drinkName}")
    public boolean guess(@PathVariable String drinkName) {
        // Parses guess.
        return gameLogic.parseGuess(drinkName);
    }

    @GetMapping("/guess/remaining")
    public int getGuess() {
        // Returns the amount of guesses left
        return gameLogic.getNumberOfGuesses();
    }

    @GetMapping("/hidden")
    public String getHidden() {
        // Returns string with hidden chars.
        return gameLogic.getHiddenString().toString();
    }

    @GetMapping("/highscore")
    public int getScore() {
        // Returns score
        return gameLogic.getHighScore();
    }

    //post score here
}
