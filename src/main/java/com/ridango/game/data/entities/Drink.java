package com.ridango.game.data.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drinks")
@Data
@Setter
@Getter
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private Long drinkId;
    private String strDrink;
    private String strGlass;

    @Column(length = 1000)
    private String strInstructions;
    private String strDrinkThumb;

    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;

    private Boolean isGuessed = false;
    public Drink(Long drinkId, String strDrink, String strGlass, String strInstructions, String strDrinkThumb) {
        this.drinkId = drinkId;
        this.strDrink = strDrink;
        this.strGlass = strGlass;
        this.strInstructions = strInstructions;
        this.strDrinkThumb = strDrinkThumb;
    }

    public Drink() {

    }
}