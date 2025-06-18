package io.github.cleri.epsicicdrpg.back.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.cleri.epsicicdrpg.back.model.Game;
import io.github.cleri.epsicicdrpg.back.service.GameService;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;



@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/game")
@RestController
public class GameController {
    private final GameService gameService;

    @PostMapping
    public ResponseEntity<Long> createGame() {
        long gameId = gameService.createGame();

        return ResponseEntity.status(201).body(gameId);
    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGame();

        if (games.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(games);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        Game gameData = gameService.getGameById(id);

        if (gameData == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(gameData);
    }

    @PostMapping("/{id}/play")
    public ResponseEntity<Game> playGame(@PathVariable Long id) {
        Game game = gameService.getGameById(id);

        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        Game updatedGame = gameService.playGame(id);
        return ResponseEntity.ok(updatedGame);
    }
}



