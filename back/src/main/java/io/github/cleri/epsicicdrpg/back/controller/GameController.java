package io.github.cleri.epsicicdrpg.back.controller;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.cleri.epsicicdrpg.back.model.Game;
import io.github.cleri.epsicicdrpg.back.service.GameService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/game")
@RestController
public class GameController {
    private final GameService gameService;

    @PostMapping("/")
    public ResponseEntity<String> createGame() {
        int gameId = gameService.createGame();

        log.error("YO");

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
        public ResponseEntity<Object> playGame(@PathVariable Long id) {
            Game game = gameService.getGameById(id);

            if (game == null) {
                return ResponseEntity.notFound().build();
            }

            if (game.getPv() <= 0) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Game over: HP is 0");
                response.put("game", game);
                return ResponseEntity.ok(response);
            }

            Game updatedGame = gameService.playGame(id);
            return ResponseEntity.ok(updatedGame);
        }

}



