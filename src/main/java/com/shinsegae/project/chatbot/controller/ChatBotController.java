package com.shinsegae.project.chatbot.controller;

import com.shinsegae.project.chatbot.service.ChatBotService;
import com.shinsegae.project.chatbot.vo.ChatBotVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Tag(name = "챗봇")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;


    @GetMapping("user/chatbot/chatbot")
    @Operation(summary = "챗봇", description = "질문과 답변 데이터를 모델에 추가")
    public String getChatbotPage(Model model) {
        // 데이터베이스에서 질문과 답변 리스트 가져오기
        List<ChatBotVO> questions = chatBotService.getAllQuestions();
        // 모델에 데이터 전달
        model.addAttribute("questions", questions);
        return "user/chatbot/chatbot"; // Thymeleaf 템플릿 이름
    }
}

