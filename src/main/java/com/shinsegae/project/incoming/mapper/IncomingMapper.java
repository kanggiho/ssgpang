package com.shinsegae.project.incoming.mapper;

import com.shinsegae.project.incoming.vo.InputVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IncomingMapper {
    List<InputVO> selectInputAll();
}