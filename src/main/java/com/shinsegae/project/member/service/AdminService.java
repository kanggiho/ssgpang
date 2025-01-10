package com.shinsegae.project.member.service;

import com.shinsegae.project.member.mapper.AdminMapper;
import com.shinsegae.project.member.vo.AdminVO;
import com.shinsegae.project.member.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    //관리자 등록
    public int insertAdmin(AdminVO adminVO) {
        String pw2 = passwordEncoder.encode(adminVO.getPassword());
        adminVO.setPassword(pw2);
        System.out.println("vo에 암호화된 pw >>> " + adminVO.getPassword());
        int result = adminMapper.insertAdmin(adminVO);
        return result;
    };

    //관리자 로그인
    public boolean login(AdminVO adminVO) {
        AdminVO adminVO1 = adminMapper.selectAdminById(adminVO.getId());
        System.out.println("========= 서비스 단 AdminVO " + adminVO1 + " =============");
        if (adminVO1 != null) {
            if (passwordEncoder.matches(adminVO.getPassword(), adminVO1.getPassword())) {
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
    public AdminVO info(int id) {
        return adminMapper.selectAdminById(id);
    }

    //관리자 삭제
    public int deleteUser(int id){
        return adminMapper.deleteAdmin(id);
    };

    //관리자 정보 업데이트
    public int updateAdmin(AdminVO adminVO) {
        return adminMapper.updateAdmin(adminVO);
    };

    //관리자 pw 찾기
    public String selectPwByAdminId (int id){
        return adminMapper.selectPwByAdminId(id);
    };

}