package io.github.cleri.epsicicdrpg.back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter
public class Game {
    @GeneratedValue
    @Id
    private Long id;

    private int pv = 6;

    private int nbSalle = 0;

    private int lastDiceRoll = 7;
}
