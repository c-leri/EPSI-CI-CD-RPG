package io.github.cleri.epsicicdrpg.back;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.cleri.epsicicdrpg.back.model.room.Room;
import io.github.cleri.epsicicdrpg.back.model.room.RoomType;
import io.github.cleri.epsicicdrpg.back.service.RoomGeneratorService;
import io.github.cleri.epsicicdrpg.back.service.ScenarioService;

@AutoConfigureTestDatabase
@SpringBootTest
class BackApplicationTests {

    RoomGeneratorService roomGeneratorService = new RoomGeneratorService();
    ScenarioService scenarioService = new ScenarioService(roomGeneratorService);

     @Test
    void testGenerateRoomsCreatesNineRooms() {
        List<Room> rooms = roomGeneratorService.generateRooms();
        assertEquals(10, rooms.size(), "Should generate exactly 9 rooms");
    }

    @Test
    void testGenerateRoomsFinalRoomIsFinalBoss() {
        List<Room> rooms = roomGeneratorService.generateRooms();
        assertEquals(RoomType.FINAL_BOSS, rooms.get(rooms.size() - 1).getType(), "Last room should be FINAL_BOSS");
    }


    @Test
    void testCreateNewScenarioGeneratesRooms() {
        List<Room> rooms = scenarioService.createNewScenario().getRooms();
        assertEquals(10, rooms.size(), "Scenario should contain 10 rooms");
    }

}
