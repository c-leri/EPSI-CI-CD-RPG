package io.github.cleri.epsicicdrpg.back.model.encounter;

import io.github.cleri.epsicicdrpg.back.model.encounter.battle.FinalBattle;

public class Fireplace extends Encounter {
    public void heal(Character character) {
        int bonus = 5;
        // int healed = (int) (character.stats.getHp() * 0.2) + bonus;
        // character.stats.setHp(character.stats.getHp() + healed);
    }

    public void pray(FinalBattle bossBattle) {
        // int reduced = (int) (bossBattle.enemy.stats.getHp() * 0.2);
        // bossBattle.enemy.stats.setHp(bossBattle.enemy.stats.getHp() - reduced);
    }
}