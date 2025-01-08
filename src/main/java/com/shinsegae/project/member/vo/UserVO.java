package com.shinsegae.project.member.vo;


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

}
