package com.shinsegae.project.common.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
   private int uid;
   private String id;
   private String password;
   private String name;
   private String tel;
   private String email;
   private String grade;

}
