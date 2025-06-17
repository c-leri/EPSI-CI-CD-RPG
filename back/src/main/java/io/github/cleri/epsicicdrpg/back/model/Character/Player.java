package io.github.cleri.epsicicdrpg.back.model.character;

import java.util.List;

import io.github.cleri.epsicicdrpg.back.model.item.Item;

public class Player extends Character{

    List<Item> Gear;

    public Player(String name, CharacterStats stats) {
        super(name, stats);
        //TODO Auto-generated constructor stub
    }
    
}
