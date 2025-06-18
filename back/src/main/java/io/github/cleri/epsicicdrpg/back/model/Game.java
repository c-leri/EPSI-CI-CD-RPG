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

    private Integer pv = 6;

    private Integer nbSalle = 0;

    private Integer lastDiceRoll = 7;
}
