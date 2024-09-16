package com.ridango.game.data.controllers;

import com.ridango.game.data.service.DrinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/setup/fetch")
    public String fetchAndSaveDrinks() {
        drinkService.saveDrinks();
        return "Drinks data has been saved successfully!";
    }

    @GetMapping("/test")
    public String fetchRandom() {

        return "Fetched random successfully.";
    }
}
