package com.shinsegae.project.chatbot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatBotVO {
    private int id;
    private String question;
    private String answer;
}
