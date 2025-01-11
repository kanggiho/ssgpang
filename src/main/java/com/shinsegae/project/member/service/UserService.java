package com.shinsegae.project.member.service;

import com.shinsegae.project.member.mapper.UserMapper;
import com.shinsegae.project.member.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    //유저 회원가입
    public int insertUser(UserVO userVO) {
        String pw2 = passwordEncoder.encode(userVO.getPassword());
        userVO.setPassword(pw2);
        System.out.println("vo에 암호화된 pw >>> " + userVO.getPassword());
        int result = userMapper.insertUser(userVO);
        return result;
    };

    //유저 로그인
    public boolean login(UserVO userVO) {
        UserVO userVO1 = userMapper.selectUserById(userVO.getId());
        System.out.println("========= 서비스 단 UserVO " + userVO1 + " =============");
        if (userVO1 != null) {
            if (passwordEncoder.matches(userVO.getPassword(), userVO1.getPassword())) {
                System.out.println("비밀번호 일치");
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //유저 회원정보
    public UserVO info(String id) {
        return userMapper.selectUserById(id);
    }

    //유저 회원정보수정
    public int updateUser(UserVO userVO) {
        if (userVO.getPassword() != null && !userVO.getPassword().isEmpty()) {
            String updatePassword = passwordEncoder.encode(userVO.getPassword());
            userVO.setPassword(updatePassword);
        }

        return userMapper.updateUser(userVO);
    }


    //유저 회원탈퇴
    public int deleteUser(String id){
        return userMapper.deleteUser(id);
    };

    public UserVO selectUserById(String id){
        return userMapper.selectUserById(id);
    };


    //유저 전화번호로 ID 찾기
    public String selectIdByUserTel(String tel){
        return userMapper.selectIdByUserTel(tel);
    };

    //유저 아이디 중복 확인
    public boolean checkId(String id) {
        return userMapper.selectUserById(id) == null;
    }

    //유저 이메일 유효성 검증
    public boolean checkEmail(String email) {
        return userMapper.selectEmailById(email) == null;
    }
}