package com.ai.chatmodels;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatmodelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatmodelsApplication.class, args);
		}


	@Bean
	public ChatClient openAIChatClient(OpenAiChatModel openAiChatModel) {
		return ChatClient.create(openAiChatModel);
	}

	@Bean
	public ChatClient anthropicChatClient(AnthropicChatModel anthropicChatModel) {
		return ChatClient.create(anthropicChatModel);
	}

}
