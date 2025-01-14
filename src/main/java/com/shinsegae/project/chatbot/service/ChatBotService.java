package com.shinsegae.project.chatbot.service;

import com.shinsegae.project.chatbot.mapper.ChatBotMapper;
import com.shinsegae.project.chatbot.vo.ChatBotVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatBotService {

    @Autowired
    private ChatBotMapper chatBotMapper;

    public List<ChatBotVO> getAllQuestions() {
        return chatBotMapper.getAllQuestions();
    }
}
