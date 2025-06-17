package io.github.cleri.epsicicdrpg.back.model.encounter;

import io.github.cleri.epsicicdrpg.back.model.item.Item;
import io.github.cleri.epsicicdrpg.back.model.item.ItemFactory;

public class Treasure extends Encounter {
    private Item item;

    public Treasure(ItemFactory itemFactory) {
        this.item = itemFactory.create("treasure", 0);
    }
}