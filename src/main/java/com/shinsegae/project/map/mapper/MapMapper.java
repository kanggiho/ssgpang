package com.shinsegae.project.map.mapper;
import com.shinsegae.project.common.dataset.vo.WarehouseVO;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MapMapper {
    WarehouseVO getWarehouseLocations();

}
