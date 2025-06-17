package io.github.cleri.epsicicdrpg.back.model;

import java.util.List;

import io.github.cleri.epsicicdrpg.back.model.room.Room;

public class Scenario {
    private List<Room> rooms;

    public Scenario(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}