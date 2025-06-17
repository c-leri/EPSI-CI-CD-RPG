package io.github.cleri.epsicicdrpg.back.model.character;

public class Enemy extends Character {
    private int exp;
    private int gold;

    
    public Enemy(String name, CharacterStats stats, int exp, int gold) {
        super(name, stats);
        this.exp = exp;
        this.gold = gold;
    }

    // Getters, Setters
}