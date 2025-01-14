package com.shinsegae.project.member.mapper;

import com.shinsegae.project.member.vo.UserVO;
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

    //유저 아이디 찾기(전화번호로)
    String selectIdByUserTel(String tel);

    //유저 이메일 검증
    boolean checkEmail(String email);

    //유저 전화번호 검증
    boolean checkTel(String tel);

    //임시 비밀번호 발급
    void updateTemporaryPassword(String email, String password);
}