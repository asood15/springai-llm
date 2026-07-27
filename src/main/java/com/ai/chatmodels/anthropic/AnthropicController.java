package com.ai.chatmodels.anthropic;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anthropic")
public class AnthropicController {

    private final ChatClient chatClient;

    public AnthropicController(@Qualifier("anthropicChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String generateResponse(@RequestParam(value = "message", defaultValue = "Tell me a fun fact about Anthropic") String prompt) {
        return chatClient.prompt(prompt).call().content();
    }
}
