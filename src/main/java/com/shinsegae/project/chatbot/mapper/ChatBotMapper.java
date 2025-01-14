package com.shinsegae.project.chatbot.mapper;

import com.shinsegae.project.chatbot.vo.ChatBotVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatBotMapper {
    // 모든 질문 목록을 가져오는 메서드
    List<ChatBotVO> getAllQuestions();
}