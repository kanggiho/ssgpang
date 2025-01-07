package com.shinsegae.project.member.service;

import com.shinsegae.project.member.mapper.MemberMapper;
import com.shinsegae.project.member.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public List<UserVO> read() {
        return memberMapper.selectUserAll();
    }

}