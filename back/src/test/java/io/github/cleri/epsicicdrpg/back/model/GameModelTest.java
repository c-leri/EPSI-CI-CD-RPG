package io.github.cleri.epsicicdrpg.back.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class GameModelTest {
    @Test
    void testGameSetterAndGetters() {
        Game game = new Game();

        
        game.setPv(5);
        game.setLastDiceRoll(4);
        game.setNbSalle(2);
        
        assertEquals(5, game.getPv());
        assertEquals(2, game.getNbSalle());
        assertEquals(4, game.getLastDiceRoll());
    }


}
