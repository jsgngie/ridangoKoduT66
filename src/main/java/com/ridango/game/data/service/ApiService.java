package com.ridango.game.data.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final RestTemplate restTemplate;

    @Value("${cocktail.api.url}")
    private String apiUrl;

    public JsonNode fetchDrinksFromApiAllAlphabet() {
        String url = apiUrl + "search.php?f=a";
        return this.restTemplate.getForObject(url, JsonNode.class);
    }
}
