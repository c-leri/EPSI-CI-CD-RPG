package io.github.cleri.epsicicdrpg.back.model.encounter;

import java.util.List;

import io.github.cleri.epsicicdrpg.back.model.item.Item;
import io.github.cleri.epsicicdrpg.back.model.item.ItemFactory;

public class Shop extends Encounter {
    private List<Item> items;

    public Shop(ItemFactory itemFactory, int roomIndex) {
        this.items = List.of(
            itemFactory.create("shop", roomIndex),
            itemFactory.create("shop", roomIndex + 1)
        );
    }
}
