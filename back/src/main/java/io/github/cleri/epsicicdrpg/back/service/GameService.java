package io.github.cleri.epsicicdrpg.back.service;

import org.springframework.stereotype.Service;
import io.github.cleri.epsicicdrpg.back.model.Game;
import io.github.cleri.epsicicdrpg.back.repository.GameRepository;
import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;

    private final Random random = new SecureRandom();

    public int createGame(){
        Game game = gameRepository.save(new Game());
        
        return game.getId().intValue();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }
    public void updateGameById(Long id, int pv, int nbSalle) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game != null) {
            game.setPv(pv);
            game.setNbSalle(nbSalle);
            gameRepository.save(game);
        }
    }

    public Game playGame(Long id) {
        Game game = gameRepository.findById(id).orElse(null);

        if (game == null) {
            return null;
        }

        int diceRoll = random.nextInt(7);
        game.setLastDiceRoll(diceRoll);

        rollDice(game, diceRoll);

        if (diceRoll != 1) {
            game.setNbSalle(game.getNbSalle() + 1); // increment room only if not game over
        }

        gameRepository.save(game);
        return game;
    }

    public void rollDice(Game game, int diceRoll){
        switch (diceRoll) {
            case 1 -> game.setPv(0); // Game over
            case 2, 3 -> game.setPv(Math.max(0, game.getPv() - 1));
            case 6 -> game.setPv(game.getPv() + 1);
            default -> {
                // Do nothing on 4, 5 and unexpected values
            }
        }
    }
}

