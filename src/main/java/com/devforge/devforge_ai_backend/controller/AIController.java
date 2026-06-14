package com.devforge.devforge_ai_backend.controller;

import com.devforge.devforge_ai_backend.service.AIGenerationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIController {

    private final AIGenerationService aiService;

    public AIController(
            AIGenerationService aiService
    ) {
        this.aiService = aiService;
    }
//    Temporary Mapping to test api key
@GetMapping("/raw")
public String raw() {

    return aiService.generateBlueprint(
            "hello"
    );
}

    @GetMapping("/test")
    public String test() {

        return aiService.generateBlueprint(
                "Food Delivery Application"
        );
    }

    @PostMapping("/generate")
    public String generate(
            @RequestBody String description
    ) {

        System.out.println(
                "========== AI GENERATE HIT =========="
        );

        System.out.println(
                "DESCRIPTION = " + description
        );

        return aiService.generateBlueprint(
                description
        );
    }
}