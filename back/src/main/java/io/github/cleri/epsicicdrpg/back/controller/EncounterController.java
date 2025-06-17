package io.github.cleri.epsicicdrpg.back.controller;
import io.github.cleri.epsicicdrpg.back.model.encounter.Encounter;
import io.github.cleri.epsicicdrpg.back.service.EncounterService;
import io.github.cleri.epsicicdrpg.back.utils.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/encounter")
@CrossOrigin(origins = "*")
public class EncounterController {

    private final EncounterService encounterService;

    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<ApiResponse<Encounter>> generateEncounter() {
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
        ApiResponse<Encounter> response = new ApiResponse<>(
                "success",
                "Encounter generated successfully.",
                null
            );
        return null;
    }
}
