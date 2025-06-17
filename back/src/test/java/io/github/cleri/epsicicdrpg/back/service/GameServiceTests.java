package io.github.cleri.epsicicdrpg.back.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.cleri.epsicicdrpg.back.model.Game;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest
class GameServiceTests {
    @Autowired
    GameService gameService;

    @CsvSource({
        "Dice 1 should set HP to 0, 1, 0",
        "Dice 2 should reduce HP by 1, 2, 5",
        "Dice 3 should reduce HP by 1, 3, 5",
        "Dice 4 should not change HP, 4, 6",
        "Dice 5 should not change HP, 5, 6",
        "Dice 6 should increase HP by 1, 6, 7"
    })
    @ParameterizedTest
    void testDiceRoll(String description, int diceRoll, int expectedHP) {
        Game game = new Game();

        gameService.rollDice(game, diceRoll);

        assertThat(game.getPv())
                .describedAs(description)
                .isEqualTo(expectedHP);
    }
}
