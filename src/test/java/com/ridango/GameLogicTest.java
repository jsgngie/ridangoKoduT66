package com.ridango;

import com.ridango.game.data.entities.Drink;
import com.ridango.game.data.service.DrinkService;
import com.ridango.game.data.service.HighscoreService;
import com.ridango.game.logic.GameLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameLogicTest {

    @Mock
    private DrinkService drinkService;

    @Mock
    private HighscoreService highscoreService;

    @InjectMocks
    private GameLogic gameLogic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testWasCorrect() {
        gameLogic.setCurrentDrinkName("Mojito");
        assertTrue(gameLogic.wasCorrect("Mojito"));
        assertFalse(gameLogic.wasCorrect("Margarita"));
    }

    @Test
    void testParseGuess_CorrectGuess() {
        Drink mockDrink = new Drink();
        mockDrink.setStrDrink("Mojito");
        when(drinkService.getRandom()).thenReturn(mockDrink);

        gameLogic.setCurrentDrink(mockDrink);
        gameLogic.setCurrentDrinkName("Mojito");

        boolean result = gameLogic.parseGuess("Mojito");

        assertTrue(result);
        assertEquals(5, gameLogic.getNumberOfGuesses());
        verify(drinkService).markGuessed(mockDrink.getId());
    }

    @Test
    void testParseGuess_IncorrectGuess() {
        Drink mockDrink = new Drink();
        mockDrink.setStrDrink("Mojito");
        when(drinkService.getRandom()).thenReturn(mockDrink);

        gameLogic.setCurrentDrink(mockDrink);
        gameLogic.setHiddenString(new StringBuilder("______"));
        gameLogic.setCurrentDrinkName("Mojito");

        boolean result = gameLogic.parseGuess("Margarita");

        assertFalse(result);
        assertEquals(4, gameLogic.getNumberOfGuesses());
    }

    @Test
    void testSetName() {
        gameLogic.setName("John Doe");

        assertEquals("John Doe", gameLogic.getHighScore().getName());
    }
}

