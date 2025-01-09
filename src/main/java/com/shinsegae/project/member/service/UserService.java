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

    public int insertUser(UserVO userVO) {
        String pw2 = passwordEncoder.encode(userVO.getPassword());
        userVO.setPassword(pw2);
        System.out.println("vo에 암호화된 pw >>> " + userVO.getPassword());
        int result = userMapper.insertUser(userVO);
        return result;
    };

    public boolean login(UserVO userVO) {
        UserVO userVO1 = userMapper.selectUserById(userVO.getId());
        if (userVO1 != null) {
            if (passwordEncoder.matches(userVO.getPassword(), userVO1.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int updateUser(UserVO userVO) {
        return userMapper.updateUser(userVO);
    };
    public int deleteUser(String id){
        return userMapper.deleteUser(id);
    };
    public UserVO selectUserById(String id){
        return userMapper.selectUserById(id);
    };
    public String selectIdByUserTel(String tel){
        return userMapper.selectIdByUserTel(tel);
    };
    public boolean checkId(String id) {
        return userMapper.selectUserById(id) == null;
    }
}