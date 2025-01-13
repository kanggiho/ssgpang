package com.shinsegae.project.chatbot.mapper;

import org.apache.ibatis.annotations.Select;

public interface ChatBotMapper {
    @Select("SELECT answer FROM questions WHERE question = #{question}")
    String getAnswerByQuestion(String question);
}
