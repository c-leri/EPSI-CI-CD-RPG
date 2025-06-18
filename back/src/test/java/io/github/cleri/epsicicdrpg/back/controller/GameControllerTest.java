package io.github.cleri.epsicicdrpg.back.controller;

import io.github.cleri.epsicicdrpg.back.model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameControllerTest {
    @Autowired
    WebTestClient client;

    @Test
    void testCreateGame() {
        EntityExchangeResult<Long> result = client.post()
            .uri("/api/game")
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Long.class)
            .returnResult();

        assertThat(result.getResponseBody()).isNotNull();

        client.get()
            .uri("/api/game/{id}", result.getResponseBody())
            .exchange()
            .expectStatus().isOk();
    }

    @Sql("classpath:/sql/GameController/game.sql")
    @Test
    void testGetAllGame() {
        EntityExchangeResult<List<Game>> result = client.get()
            .uri("/api/game/GetAll")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Game.class)
            .returnResult();

        assertThat(result.getResponseBody()).hasSize(1);
    }

    @Test
    void testGetAllGameNoContent() {
        client.get()
            .uri("/api/game/GetAll")
            .exchange()
            .expectStatus().isNoContent();
    }

    @Sql("classpath:/sql/GameController/game.sql")
    @Test
    void testGetGame() {
        EntityExchangeResult<Game> result = client.get()
            .uri("/api/game/{id}", 1)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Game.class)
            .returnResult();

        assertThat(result.getResponseBody()).isNotNull();
        assertThat(result.getResponseBody().getPv()).isEqualTo(4);
        assertThat(result.getResponseBody().getNbSalle()).isEqualTo(13);
    }

    @Sql("classpath:/sql/GameController/game.sql")
    @Test
    void testGetGameNotFound() {
        client.get()
            .uri("/api/game/{id}", 2)
            .exchange()
            .expectStatus().isNotFound();
    }
}
