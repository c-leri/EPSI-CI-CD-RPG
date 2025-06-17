package io.github.cleri.epsicicdrpg.back.service;

import java.util.*;

import org.springframework.stereotype.Service;

import io.github.cleri.epsicicdrpg.back.model.room.Room;
import io.github.cleri.epsicicdrpg.back.model.room.RoomType;

@Service
public class RoomGeneratorService {

    private static final Random random = new Random();

    public List<Room> generateRooms() {
        List<Room> rooms = new ArrayList<>();

        boolean hasMiniBoss = false;
        boolean fireplaceAfterMiniBoss = false;

        for (int i = 1; i <= 9; i++) {
            RoomType roomType;
            
            // Un miniboss peut apparaitre entre les salles 3 et 8
            if (i >= 3 && i <= 8 && !hasMiniBoss && random.nextDouble() < 0.2) {
                roomType = RoomType.MINI_BOSS;
                hasMiniBoss = true;
                fireplaceAfterMiniBoss = true;
            } else if (fireplaceAfterMiniBoss) {
                // Un feu de camp est systématiquement après un miniboss
                roomType = RoomType.FIREPLACE;
                fireplaceAfterMiniBoss = false;
            } else {
                roomType = getRandomRoomType(i);
            }

            rooms.add(new Room(i, roomType));
        }

        // Final boss room
        rooms.add(new Room(10, RoomType.FINAL_BOSS));

        return rooms;
    }

    private RoomType getRandomRoomType(int roomNumber) {
        List<RoomType> possibleTypes = new ArrayList<>();

        possibleTypes.add(RoomType.BATTLE); // always possible

        if (roomNumber == 3 || roomNumber == 8) {
            possibleTypes.add(RoomType.SHOP);
            possibleTypes.add(RoomType.MYSTERY);
        }
        if (roomNumber == 4 || roomNumber == 7) {
            possibleTypes.add(RoomType.TREASURE);
        }
        if(roomNumber == 5 || roomNumber == 9){
            possibleTypes.add(RoomType.FIREPLACE);
        }

        return possibleTypes.get(random.nextInt(possibleTypes.size()));
    }
}