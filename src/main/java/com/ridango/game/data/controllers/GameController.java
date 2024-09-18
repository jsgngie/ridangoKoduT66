package com.ridango.game.data.controllers;

import com.ridango.game.data.service.DrinkService;
import com.ridango.game.logic.GameLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GameController {

    private final DrinkService drinkService;

    private final GameLogic gameLogic;

    @GetMapping("/setup")
    public Boolean fetchAndSaveDrinks() {
        try {
            //Check if game is already set up
            gameLogic.getCocktail();
            gameLogic.endGame(true); // End game just in case
            return true;
        } catch (Exception e) {
            drinkService.populateDatabase();
            try {
                gameLogic.getCocktail();
            } catch (Exception e2) {
                return false;
            }
            return true;
        }
    }

    @PostMapping("/setup/{name}")
    public void setName(@PathVariable String name) {
        gameLogic.setName(name);
    }

    @PostMapping("/guess/{drinkName}")
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
        return gameLogic.getHighScore().getScore();
    }

    @PostMapping("/end")
    public void end() {
        gameLogic.endGame(false);
    }

    @GetMapping("/instructions")
    public String getInstructions() {
        return gameLogic.getCurrentDrink().getStrInstructions();
    }
}
