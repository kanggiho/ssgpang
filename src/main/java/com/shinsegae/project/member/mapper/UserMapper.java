package com.shinsegae.project.member.mapper;

import com.shinsegae.project.member.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    int insertUser(UserVO userVO);
    int updateUser(UserVO userVO);
    int deleteUser(String id);
    UserVO selectUserById(String id);
    String selectIdByUserTel(String tel);
}