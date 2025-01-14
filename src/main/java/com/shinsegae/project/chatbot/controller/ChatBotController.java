package com.shinsegae.project.chatbot.controller;

import com.shinsegae.project.chatbot.service.ChatBotService;
import com.shinsegae.project.chatbot.vo.ChatBotVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/chatbot")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;

    @GetMapping("chatbot")
    public String chatbotPage() {
        return "user/chatbot/chatbot";
    }

    @PostMapping("/ask")
    public String askQuestion(@RequestBody ChatBotVO chatBotVO) {
        String answer = chatBotService.getAnswer(chatBotVO.getQuestion());
        return answer != null ? answer : "죄송합니다. 질문을 이해할 수 없습니다.";
    }

    // 모든 준비된 질문 목록을 반환하는 API
    @GetMapping("/questions")
    public List<ChatBotVO> getQuestions() {
        return chatBotService.getAllQuestions();
    }
}

