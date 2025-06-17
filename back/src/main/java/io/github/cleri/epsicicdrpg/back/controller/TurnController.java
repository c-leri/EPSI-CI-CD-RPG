package io.github.cleri.epsicicdrpg.back.controller;

import io.github.cleri.epsicicdrpg.back.model.turn.Turn;
import io.github.cleri.epsicicdrpg.back.service.turn.TurnService;
import io.github.cleri.epsicicdrpg.back.utils.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/turn")
@CrossOrigin(origins = "*")
public class TurnController {

    private final TurnService turnService;

    public TurnController(TurnService turnService) {
        this.turnService = turnService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<ApiResponse<Turn>> generateTurn() {
        // try {
        //     Scenario scenario = scenarioService.createNewScenario();
        //     ApiResponse<Scenario> response = new ApiResponse<>(
        //         "success",
        //         "Scenario generated successfully.",
        //         scenario
        //     );
        //     return ResponseEntity.ok(response);
        // } catch (Exception e) {
        //     ApiResponse<Scenario> errorResponse = new ApiResponse<>(
        //         "error",
        //         "Failed to generate scenario.",
        //         null
        //     );
        //     return ResponseEntity.status(500).body(errorResponse);
        // }
        ApiResponse<Turn> response = new ApiResponse<>(
                "success",
                "Turn generated successfully.",
                null
            );
        return null;
    }
}
