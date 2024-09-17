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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drinkId")
    private Drink drink;

    private String name;
    private String measure;

    public Ingredient(Drink drink, String name, String measure) {
        this.drink = drink;
        this.name = name;
        this.measure = measure;
    }

    public Ingredient() {

    }
}