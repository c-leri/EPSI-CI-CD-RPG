package io.github.cleri.epsicicdrpg.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.cleri.epsicicdrpg.back.model.Sauvegarde;
import io.github.cleri.epsicicdrpg.back.service.SauvegardeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/sauvegarde")
public class SauvegardeController {

    @Autowired
    private SauvegardeService sauvegardeService;

    @PostMapping("/game")
    public ResponseEntity<String> createGame() {
        int gameId = sauvegardeService.createSauvegarde();
        return ResponseEntity.status(201).body(Integer.toString(gameId));
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Sauvegarde> getGame(@PathVariable Long id) {
        Sauvegarde gameData = sauvegardeService.getSauvegardeById(id);
        if (gameData == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameData);
    }
}



