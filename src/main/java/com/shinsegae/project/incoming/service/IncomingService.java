package com.shinsegae.project.incoming.service;

import com.shinsegae.project.incoming.mapper.IncomingMapper;
import com.shinsegae.project.incoming.vo.IncomingInputVO;
import com.shinsegae.project.incoming.vo.IncomingManagementDTO;
import com.shinsegae.project.incoming.vo.IncomingRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomingService {
    private final IncomingMapper incomingMapper;

    public List<IncomingManagementDTO> read() {
        return incomingMapper.selectInputAll();
    }
    public List<IncomingRequestDTO> insert() {
        return incomingMapper.selectRequestAll();
    }


    public void saveIncomingRequest(IncomingInputVO incominginputVO) {
        incomingMapper.insertIncomingRequest(incominginputVO);
    }

}