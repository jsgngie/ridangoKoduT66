package com.ridango.game;

import com.ridango.game.data.service.ApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CocktailGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailGameApplication.class, args);
	}
}
