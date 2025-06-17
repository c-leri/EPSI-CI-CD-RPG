package io.github.cleri.epsicicdrpg.back;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.cleri.epsicicdrpg.back.model.Game;
import io.github.cleri.epsicicdrpg.back.service.GameService;

@AutoConfigureTestDatabase
@SpringBootTest
public class GameTests {
    @Test
    void testDiceRoll(){
        GameService gameService = new GameService(null);

        // Dice roll 1: game over
        Game game1 = new Game();
        gameService.rollDice(game1, 1);
        assertEquals(0, game1.getPv(), "Dice 1 should set HP to 0");

        // Dice roll 2: lose 1 HP
        Game game2 = new Game();
        gameService.rollDice(game2, 2);
        assertEquals(5, game2.getPv(), "Dice 2 should reduce HP by 1");

        // Dice roll 3: lose 1 HP
        Game game3 = new Game();
        gameService.rollDice(game3, 3);
        assertEquals(5, game3.getPv(), "Dice 3 should reduce HP by 1");

        // Dice roll 4: nothing
        Game game4 = new Game();
        gameService.rollDice(game4, 4);
        assertEquals(6, game4.getPv(), "Dice 4 should not change HP");

        // Dice roll 5: nothing
        Game game5 = new Game();
        gameService.rollDice(game5, 5);
        assertEquals(6, game5.getPv(), "Dice 5 should not change HP");

        // Dice roll 6: gain 1 HP
        Game game6 = new Game();
        gameService.rollDice(game6, 6);
        assertEquals(7, game6.getPv(), "Dice 6 should increase HP by 1");
        
    }
}
