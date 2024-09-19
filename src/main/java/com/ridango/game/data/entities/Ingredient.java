package com.ridango.game.data.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Data
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "drinkId")
    private Long drinkId;

    private String name;
    private String measure;

    public Ingredient(Long drinkId, String name, String measure) {
        this.drinkId = drinkId;
        this.name = name;
        this.measure = measure;
    }

    public Ingredient() {

    }
}