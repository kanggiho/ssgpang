package com.shinsegae.project.incoming.mapper;

import com.shinsegae.project.incoming.vo.IncomingInputVO;
import com.shinsegae.project.incoming.vo.IncomingManagementDTO;
import com.shinsegae.project.incoming.vo.IncomingRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IncomingMapper {
    List<IncomingManagementDTO> selectInputAll();
    List<IncomingRequestDTO> selectRequestAll();
    int insertIncomingRequest(IncomingInputVO incomingInputVO);


}
