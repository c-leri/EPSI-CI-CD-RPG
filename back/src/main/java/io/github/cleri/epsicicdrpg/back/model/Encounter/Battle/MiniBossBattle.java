package io.github.cleri.epsicicdrpg.back.model.encounter.battle;

import io.github.cleri.epsicicdrpg.back.model.character.EnemyFactory;
import io.github.cleri.epsicicdrpg.back.model.item.ItemFactory;
import io.github.cleri.epsicicdrpg.back.model.item.Reward;

public class MiniBossBattle extends Battle{
    public MiniBossBattle(EnemyFactory enemyFactory, ItemFactory itemFactory, int roomIndex) {
        this.enemy = enemyFactory.create("hard", roomIndex);
        this.reward = new Reward(itemFactory.create("hard", roomIndex));
    }
}
