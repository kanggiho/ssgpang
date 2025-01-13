package com.shinsegae.project.board.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {
   private int no;
   private String title;
   private String content;
   private String writer;
   private String postDate;
}
