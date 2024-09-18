package com.ridango.game.data.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final RestTemplate restTemplate;

    @Value("${cocktail.api.url}")
    private String apiUrl;

    public List<JsonNode> fetchDrinksFromApiAllAlphabet() {
        List<JsonNode> allDrinks = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // Loop through characters from 'a' to 'z'
        for (char letter = 'a'; letter <= 'z'; letter++) {
            String url = apiUrl + "search.php?f=" + letter;
            JsonNode drinksForLetter = this.restTemplate.getForObject(url, JsonNode.class);
            if (drinksForLetter != null && !drinksForLetter.isNull()) {
                allDrinks.add(drinksForLetter);
            }
        }

        return allDrinks;
    }
}
