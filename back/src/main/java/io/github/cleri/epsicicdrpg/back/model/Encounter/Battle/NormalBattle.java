package io.github.cleri.epsicicdrpg.back.model.encounter.battle;

import io.github.cleri.epsicicdrpg.back.model.character.EnemyFactory;
import io.github.cleri.epsicicdrpg.back.model.item.ItemFactory;
import io.github.cleri.epsicicdrpg.back.model.item.Reward;

public class NormalBattle extends Battle{
    public NormalBattle(EnemyFactory enemyFactory, ItemFactory itemFactory, int roomIndex) {
        this.enemy = enemyFactory.create("easy", roomIndex);
        this.reward = new Reward(itemFactory.create("easy", roomIndex));
    }
}
