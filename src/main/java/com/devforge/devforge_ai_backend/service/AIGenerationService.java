package com.devforge.devforge_ai_backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import java.time.Duration;

@Service
public class AIGenerationService {

    @Value("${openrouter.api.key}")
    private String apiKey;

    private final WebClient webClient =
            WebClient.builder()
                    .clientConnector(
                            new ReactorClientHttpConnector(
                                    HttpClient.create()
                                            .compress(true)
                            )
                    )
                    .baseUrl("https://openrouter.ai/api/v1")
                    .build();

    private final ObjectMapper objectMapper =
            new ObjectMapper();

    public String generateBlueprint(
            String description
    ) {

        try {

            String prompt =
                    """
Generate a clean software project blueprint.

IMPORTANT RULES:

- Use simple plain English.
- Do NOT use Markdown tables.
- Do NOT use LaTeX.
- Do NOT use symbols like $\\rightarrow$.
- Use normal bullets only.
- Keep it beginner friendly.
- Keep it concise.
- Maximum 600 words.

Include:

1. Project Overview
2. System Architecture
3. Database Design
4. REST APIs
5. Technology Stack
6. Development Roadmap

Project Description:

""" + description;

            String body =
                    """
                    {
                      "model":"openrouter/free",
                      "messages":[
                        {
                          "role":"user",
                          "content":"%s"
                        }
                      ]
                    }
                    """.formatted(
                            prompt.replace("\"", "\\\"")
                    );

            System.out.println("========== AI GENERATE HIT ==========");
            System.out.println("DESCRIPTION = " + description);

            String responseBody =
                    webClient.post()
                            .uri("/chat/completions")
                            .header(
                                    "Authorization",
                                    "Bearer " + apiKey
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .header(
                                    "HTTP-Referer",
                                    "http://localhost:5173"
                            )
                            .header(
                                    "X-Title",
                                    "DevForge AI"
                            )
                            .bodyValue(body)
                            .retrieve()
                            .bodyToMono(String.class)
                            .timeout(Duration.ofSeconds(120))
                            .block(Duration.ofSeconds(120));

            System.out.println("========== OPENROUTER RESPONSE ==========");
            System.out.println(responseBody);

            JsonNode root =
                    objectMapper.readTree(responseBody);

            if (!root.has("choices")) {

                return "OpenRouter Error:\n\n"
                        + responseBody;
            }

            return root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

        } catch (Exception e) {

            e.printStackTrace();

            return "AI Generation Failed:\n\n"
                    + e.toString();
        }
    }
}