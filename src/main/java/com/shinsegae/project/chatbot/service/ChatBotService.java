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

    // 사용자가 보낸 질문에 대한 답변을 반환하는 메서드
    public String getAnswer(String question) {
        ChatBotVO chatBotVO = chatBotMapper.findAnswerByQuestion(question);
        return chatBotVO != null ? chatBotVO.getAnswer() : null;
    }

    // 모든 준비된 질문 목록을 반환하는 메서드
    public List<ChatBotVO> getAllQuestions() {
        return chatBotMapper.findAllQuestions();
    }
}
