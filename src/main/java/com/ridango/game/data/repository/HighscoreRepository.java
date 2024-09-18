package com.ridango.game.data.repository;

import com.ridango.game.data.entities.Highscore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighscoreRepository extends JpaRepository<Highscore, Long> {
}
