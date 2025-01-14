package com.shinsegae.project.chatbot.mapper;

import com.shinsegae.project.chatbot.vo.ChatBotVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatBotMapper {
    // 주어진 질문에 대한 답변을 찾는 메서드
    ChatBotVO findAnswerByQuestion(String question);

    // 모든 질문 목록을 반환하는 메서드
    List<ChatBotVO> findAllQuestions();
}