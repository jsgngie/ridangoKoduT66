package com.ridango.game.data.service;

import com.ridango.game.data.entities.Highscore;
import com.ridango.game.data.repository.HighscoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HighscoreService {
    private final HighscoreRepository highscoreRepository;

    public void postScore(Highscore highscore) {
        highscoreRepository.save(highscore);
    }

    public List<Highscore> getHighScores() {
        return highscoreRepository.findAll();
    }
}
