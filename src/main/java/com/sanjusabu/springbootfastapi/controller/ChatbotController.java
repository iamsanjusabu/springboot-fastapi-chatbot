package com.sanjusabu.springbootfastapi.controller;

import com.sanjusabu.springbootfastapi.dto.RequestQuestion;
import com.sanjusabu.springbootfastapi.service.ChatbotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping
    public String chatbot(@RequestBody RequestQuestion question) {
        return chatbotService.postRawTextToFastAPI(question.getQuestion());
    }
}
