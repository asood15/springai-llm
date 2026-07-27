package com.ai.chatmodels.openai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openai/v1/")
public class OpenAIController {

    private final ChatClient chatClient;

    public OpenAIController(@Qualifier("openAIChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping()
    public String generateResponse(@RequestParam(value = "message", defaultValue = "Tell me a fun fact about OpenAI") String prompt) {
        return chatClient.prompt(prompt).call().content();
    }
}
