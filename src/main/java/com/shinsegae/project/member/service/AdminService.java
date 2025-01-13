package com.shinsegae.project.member.service;

import com.shinsegae.project.member.mapper.AdminMapper;
import com.shinsegae.project.member.vo.AdminVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    //관리자 등록
    public int insertAdmin(AdminVO adminVO) {
        int result = adminMapper.insertAdmin(adminVO);
        return result;
    };

    //관리자 로그인
    public boolean login(AdminVO adminVO) {
        AdminVO adminVO1 = adminMapper.selectAdminById(adminVO.getId());
        System.out.println("========= 서비스 단 AdminVO " + adminVO1 + " =============");
        if (adminVO1 != null) {
            if ((adminVO.getPassword().equals(adminVO1.getPassword()))) {
                System.out.println("비밀번호 일치");
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //관리자 정보 확인
    public AdminVO info(String id) {
        return adminMapper.selectAdminById(id);
    }

    //관리자 삭제
    public int deleteAdmin(String id){
        return adminMapper.deleteAdmin(id);
    };

    //관리자 정보 업데이트
    public int updateAdmin(AdminVO adminVO) {
        return adminMapper.updateAdmin(adminVO);
    };

    //관리자 pw 찾기
    public String selectPwByAdminId (String id){
        return adminMapper.selectPwByAdminId(id);
    };

}