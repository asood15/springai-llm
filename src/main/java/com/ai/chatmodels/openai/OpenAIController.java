package com.ai.chatmodels.openai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final ChatClient chatClient;

    public OpenAIController(@Qualifier("openAIChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String generateResponse(@RequestParam(value = "message", defaultValue = "Tell me a fun fact about OpenAI") String prompt) {
        return chatClient.prompt(prompt).call().content();
    }
}
