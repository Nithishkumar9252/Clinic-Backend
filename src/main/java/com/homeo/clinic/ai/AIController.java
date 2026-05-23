package com.homeo.clinic.ai;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

@RequestMapping("/api/ai")

@CrossOrigin("*")

public class AIController {

    private final AIService aiService;

    public AIController(

            AIService aiService

    ) {

        this.aiService = aiService;
    }

    /* ===================================== */
    /* CHAT AI */
    /* ===================================== */

    @PostMapping("/chat")

    public Map<String,String> chat(

            @RequestBody
            Map<String,String> body

    ) {

        String message =
                body.get("message");

        String reply =
                aiService.askAI(message);

        return Map.of(

                "reply",

                reply
        );
    }

    /* ===================================== */
    /* ANALYZE PATIENT */
    /* ===================================== */

    @PostMapping("/analyze")

    public Map<String,String> analyze(

            @RequestBody
            Map<String,String> body

    ) {

        String patientData =
                body.get("patientData");

        String reply =
                aiService.analyzePatient(patientData);

        return Map.of(

                "reply",

                reply
        );
    }
}