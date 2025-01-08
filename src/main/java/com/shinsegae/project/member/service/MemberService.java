package com.shinsegae.project.member.service;

import com.shinsegae.project.member.mapper.MemberMapper;
import com.shinsegae.project.member.vo.MemberVO;
import com.shinsegae.project.member.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public int insertMember(MemberVO memberVO){
        return memberMapper.insertMember(memberVO);
    };
    public int updateMember(MemberVO memberVO){
        return memberMapper.updateMember(memberVO);
    };
    public int deleteMember(String id){
        return memberMapper.deleteMember(id);
    };
    public MemberVO selectMemberById(String id){
        return memberMapper.selectMemberById(id);
    };
    public String selectIdByUserTel(String tel){
        return memberMapper.selectIdByUserTel(tel);
    };
}