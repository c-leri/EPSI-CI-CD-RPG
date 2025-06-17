package io.github.cleri.epsicicdrpg.back.model.item;

import io.github.cleri.epsicicdrpg.back.model.character.CharacterStats;

public class ItemFactory {
    public Item create(String type, int roomIndex) {
        // CharacterStats stats = new CharacterStats(0, 0); // Fill as needed
        int sellPrice = 50 + 10 * roomIndex;
        String gearSlot = "weapon"; // or armor, etc.
        return new Item();
    }
}