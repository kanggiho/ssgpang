package com.shinsegae.project.member.mapper;

import com.shinsegae.project.member.vo.AdminVO;
import com.shinsegae.project.member.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdminMapper {
    int insertAdmin(AdminVO adminVO);
    int updateAdmin(AdminVO adminVO);
    int deleteAdmin(int id);
    AdminVO selectAdminById(int id);
    String selectPwByAdminId(int id);
}