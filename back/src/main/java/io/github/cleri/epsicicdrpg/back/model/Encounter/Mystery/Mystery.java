package io.github.cleri.epsicicdrpg.back.model.encounter.mystery;

import io.github.cleri.epsicicdrpg.back.model.encounter.Encounter;

public class Mystery extends Encounter {
    private MysteryEvent event;

    public Mystery(int roomIndex) {
        this.event = new MysteryEvent();
    }
}