package com.shinsegae.project.map.mapper;

import com.shinsegae.project.map.vo.WarehouseInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MapMapper {
   List<WarehouseInfoDTO> getWarehouseData(); // 이름과 위치 조회

}

