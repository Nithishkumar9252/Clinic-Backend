package com.homeo.clinic.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    /* ===================================== */
    /* OPENROUTER API KEY */
    /* ===================================== */

    @Value("${openrouter.api.key}")

    private String API_KEY;

    /* ===================================== */
    /* CHAT AI */
    /* ===================================== */

    public String askAI(

            String userMessage

    ) {

        String prompt = """

                You are an AI Homeopathy Assistant.

                Responsibilities:

                - Suggest medicines
                - Suggest dosage
                - Suggest advice
                - Suggest followup

                Rules:

                - Keep responses concise
                - Keep responses professional
                - Mention doctor verification required

                User Question:
                """

                + userMessage;

        return callAI(prompt);
    }

    /* ===================================== */
    /* PATIENT ANALYSIS */
    /* ===================================== */

    public String analyzePatient(

            String patientData

    ) {

        String prompt = """

                You are an expert AI Homeopathy Doctor.

                Analyze the patient completely.

                Give:

                1. Possible disease
                2. Disease severity
                3. Root cause
                4. Suggested homeopathy medicines
                5. Dosage
                6. Diet advice
                7. Follow-up advice
                8. Risk factors

                Keep response professional.

                Mention:
                "Doctor verification required."

                Patient Details:
                """

                + patientData;

        return callAI(prompt);
    }

    /* ===================================== */
    /* COMMON OPENROUTER METHOD */
    /* ===================================== */

    private String callAI(

            String prompt

    ) {

        /* ===================================== */
        /* OPENROUTER URL */
        /* ===================================== */

        String url =

                "https://openrouter.ai/api/v1/chat/completions";

        /* ===================================== */
        /* HEADERS */
        /* ===================================== */

        HttpHeaders headers =
                new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON
        );

        headers.setBearerAuth(API_KEY);

        headers.add(
                "HTTP-Referer",
                "http://localhost:4200"
        );

        headers.add(
                "X-Title",
                "Homeo AI Clinic"
        );

        /* ===================================== */
        /* MESSAGE */
        /* ===================================== */

        Map<String,Object> message =

                Map.of(

                        "role",
                        "user",

                        "content",
                        prompt
                );

        /* ===================================== */
        /* REQUEST BODY */
        /* ===================================== */

        Map<String,Object> body =

                Map.of(

                        /*
                        =====================================
                        FREE MODEL
                        =====================================
                        */

                        "model",
                        "openai/gpt-3.5-turbo",

                        "messages",
                        List.of(message),

                        "temperature",
                        0.7,

                        "max_tokens",
                        300
                );

        HttpEntity<Map<String,Object>> entity =

                new HttpEntity<>(
                        body,
                        headers
                );

        RestTemplate restTemplate =
                new RestTemplate();

        /* ===================================== */
        /* RETRY LOGIC */
        /* ===================================== */

        int maxRetries = 3;

        for (

                int attempt = 1;

                attempt <= maxRetries;

                attempt++

        ) {

            try {

                System.out.println(
                        "Calling OpenRouter API... Attempt: "
                                + attempt
                );

                /* ===================================== */
                /* API CALL */
                /* ===================================== */

                ResponseEntity<Map> response =

                        restTemplate.postForEntity(

                                url,

                                entity,

                                Map.class
                        );

                /* ===================================== */
                /* RESPONSE BODY */
                /* ===================================== */

                Map responseBody =
                        response.getBody();

                if (

                        responseBody == null ||

                                !responseBody.containsKey(
                                        "choices"
                                )

                ) {

                    return """

                            AI returned empty response.
                            """;
                }

                /* ===================================== */
                /* CHOICES */
                /* ===================================== */

                List<Map<String,Object>> choices =

                        (List<Map<String,Object>>)

                                responseBody.get(
                                        "choices"
                                );

                if (

                        choices == null ||

                                choices.isEmpty()

                ) {

                    return """

                            No AI choices returned.
                            """;
                }

                /* ===================================== */
                /* FIRST CHOICE */
                /* ===================================== */

                Map<String,Object> firstChoice =

                        choices.get(0);

                Map<String,Object> messageObj =

                        (Map<String,Object>)

                                firstChoice.get(
                                        "message"
                                );

                if (

                        messageObj == null ||

                                !messageObj.containsKey(
                                        "content"
                                )

                ) {

                    return """

                            Empty AI message response.
                            """;
                }

                /* ===================================== */
                /* FINAL RESPONSE */
                /* ===================================== */

                return messageObj

                        .get("content")

                        .toString();

            }

            catch (Exception e) {

                e.printStackTrace();

                System.out.println(
                        "OpenRouter API failed. Retry: "
                                + attempt
                );

                /* ===================================== */
                /* WAIT BEFORE RETRY */
                /* ===================================== */

                try {

                    Thread.sleep(2000);

                } catch (InterruptedException ex) {

                    Thread.currentThread().interrupt();
                }

                /* ===================================== */
                /* LAST ATTEMPT */
                /* ===================================== */

                if (attempt == maxRetries) {

                    return """

                            OpenRouter AI Service Unavailable.

                            Possible reasons:

                            • Invalid OpenRouter API key
                            • Internet connection issue
                            • API quota exceeded
                            • OpenRouter temporary outage

                            Please try again later.
                            """;
                }
            }
        }

        return """

                Unknown AI error occurred.
                """;
    }
}