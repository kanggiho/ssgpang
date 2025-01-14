package com.shinsegae.project.member.mapper;

import com.shinsegae.project.member.vo.AdminVO;
import com.shinsegae.project.member.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdminMapper {
    //관리자 등록
    int insertAdmin(AdminVO adminVO);
    //관리자 정보 수정
    int updateAdmin(AdminVO adminVO);
    //관리자 탈퇴
    int deleteAdmin(String id);
    //관리자 로그인
    AdminVO selectAdminById(String id);
    //관리자 아이디 찾기
    String selectPwByAdminId(String id);
}