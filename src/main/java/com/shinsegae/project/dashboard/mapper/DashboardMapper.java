package com.shinsegae.project.dashboard.mapper;

import com.shinsegae.project.dashboard.vo.OutputVO1;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashboardMapper {
    int selectTotalInventory();
    int selectInputQuantity();
    Integer selectOutputQuantity();
    Integer selectOutputByStatus();
}