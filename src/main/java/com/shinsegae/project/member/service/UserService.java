package com.shinsegae.project.member.service;

import com.shinsegae.project.member.mapper.UserMapper;
import com.shinsegae.project.member.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public int insertUser(UserVO userVO) {
        return userMapper.insertUser(userVO);
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
}