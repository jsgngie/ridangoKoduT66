package com.ridango.game.data.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "highscores")
@Data
@Setter
@Getter
public class Highscore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private int score;
    
    public Highscore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Highscore() {
        
    }
}
