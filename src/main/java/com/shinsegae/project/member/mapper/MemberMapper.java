package com.shinsegae.project.member.mapper;

import com.shinsegae.project.member.vo.MemberVO;
import com.shinsegae.project.member.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    int insertMember(MemberVO memberVO);
    int updateMember(MemberVO memberVO);
}