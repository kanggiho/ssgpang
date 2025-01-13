package com.shinsegae.project.member.mapper;

import com.shinsegae.project.member.vo.UserVO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    //유저 회원가입
    int insertUser(UserVO userVO);
    //유저 회원정보수정
    int updateUser(UserVO userVO);
    //유저 회원탈퇴
    int deleteUser(String id);

    //유저 로그인
    UserVO selectUserById(String id);
    //유저 이메일 확인
    UserVO selectEmailById(String id);
    //유저 아이디 찾기(전화번호로)
    String selectIdByUserTel(String tel);

    boolean isEmailExists(String email);

    void updateTemporaryPassword(String email, String password);
}