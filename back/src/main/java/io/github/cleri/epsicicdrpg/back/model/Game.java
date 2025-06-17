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
    private int pv;
    private int nbSalle;

    public Game() {
        this.pv = 6; 
        this.nbSalle = 0; 
    }


}
