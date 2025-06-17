package io.github.cleri.epsicicdrpg.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.cleri.epsicicdrpg.back.model.Game;
import io.github.cleri.epsicicdrpg.back.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/")
    public ResponseEntity<String> createGame() {
        int gameId = gameService.createGame();
        return ResponseEntity.status(201).body(Integer.toString(gameId));
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
        Game game = gameService.playGame(id);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }
}



