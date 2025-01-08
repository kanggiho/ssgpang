package com.shinsegae.project.incoming.service;

import com.shinsegae.project.incoming.mapper.IncomingMapper;
import com.shinsegae.project.incoming.vo.InputVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomingService {
    private final IncomingMapper incomingMapper;

    public List<InputVO> read() {
        return incomingMapper.selectInputAll();
    }

}