package io.github.cleri.epsicicdrpg.back.service;

import org.springframework.stereotype.Service;

import io.github.cleri.epsicicdrpg.back.model.Scenario;
import io.github.cleri.epsicicdrpg.back.model.room.Room;

import java.util.List;

@Service
public class ScenarioService {

    private final RoomGeneratorService roomGeneratorService;

    public ScenarioService(RoomGeneratorService roomGeneratorService) {
        this.roomGeneratorService = roomGeneratorService;
    }

    public Scenario createNewScenario() {
        List<Room> rooms = roomGeneratorService.generateRooms();
        return new Scenario(rooms);
    }
}