package com.ai.chatmodels.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/prompt")
public class PromptController {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/systemprompt.st")
    private Resource systemPrompt;

    public PromptController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt")
    public Map<String, String> generatePromptResponse(@RequestParam(value = "tone", defaultValue = "funny") String tone,
                                                      @RequestParam(value = "message", defaultValue = "Tell me a fun fact about OpenAI") String userPrompt) {
        var userMessage = new UserMessage(userPrompt);
        var PromptTemplate = new PromptTemplate(systemPrompt);
        var systemMessage = PromptTemplate.create(Map.of("tone", tone)).getSystemMessage();
        Prompt prompt = new Prompt(List.of(systemMessage,userMessage));

        ChatResponse chatResponse = chatClient.prompt(prompt).call().chatResponse();
        if (chatResponse == null) {
            return null;
        }
        ChatResponseMetadata metadata = chatResponse.getMetadata();
        Map<String, String> result = new HashMap<>();
        result.put("result", Objects.requireNonNull(chatResponse.getResult()).getOutput().getText());
        result.put("model", metadata.getModel());
        result.put("promptTokens", metadata.getUsage().getPromptTokens().toString());
        result.put("completionTokens", metadata.getUsage().getCompletionTokens().toString());

        return result;
    }
}
