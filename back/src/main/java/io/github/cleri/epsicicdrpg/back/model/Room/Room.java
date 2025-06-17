package io.github.cleri.epsicicdrpg.back.model.room;

public class Room {
    private int index;
    private RoomType type;

    public Room(int index, RoomType type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public RoomType getType() {
        return type;
    }
}