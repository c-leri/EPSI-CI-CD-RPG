package io.github.cleri.epsicicdrpg.back.controller;

import io.github.cleri.epsicicdrpg.back.model.Scenario;
import io.github.cleri.epsicicdrpg.back.service.ScenarioService;
import io.github.cleri.epsicicdrpg.back.utils.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scenario")
@CrossOrigin(origins = "*")
public class ScenarioController {

    private final ScenarioService scenarioService;

    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @GetMapping("/new")
    public ResponseEntity<ApiResponse<Scenario>> generateScenario() {
        try {
            Scenario scenario = scenarioService.createNewScenario();
            ApiResponse<Scenario> response = new ApiResponse<>(
                "success",
                "Scenario generated successfully.",
                scenario
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Scenario> errorResponse = new ApiResponse<>(
                "error",
                "Failed to generate scenario.",
                null
            );
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
