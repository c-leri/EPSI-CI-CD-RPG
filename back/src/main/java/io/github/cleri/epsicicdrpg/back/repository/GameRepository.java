package io.github.cleri.epsicicdrpg.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.cleri.epsicicdrpg.back.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {


}
