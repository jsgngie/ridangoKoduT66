package com.ridango.game.data.repository;

import com.ridango.game.data.entities.Drink;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    @Query(value = "SELECT * FROM drinks d WHERE d.is_guessed = false ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Drink> fetchRandomDrink();

    @Modifying
    @Transactional
    @Query("UPDATE Drink d SET d.isGuessed = true WHERE d.id = :id")
    void markAsGuessed(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Drink d SET d.isGuessed = false WHERE d.isGuessed = true")
    void cleanGuessed();

}
