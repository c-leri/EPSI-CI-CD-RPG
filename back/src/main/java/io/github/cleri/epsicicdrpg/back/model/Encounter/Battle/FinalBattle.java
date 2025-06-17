package io.github.cleri.epsicicdrpg.back.model.encounter.battle;

import io.github.cleri.epsicicdrpg.back.model.character.EnemyFactory;

public class FinalBattle extends Battle{
    public FinalBattle(EnemyFactory enemyFactory) {
        this.enemy = enemyFactory.create("nightmare", 0);
        this.reward = null; // Optional
    }

    public void triggerWinConditionOnKill() {
        // Implement logic
    }
}
