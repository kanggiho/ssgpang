package com.shinsegae.project.dashboard.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {
    int selectTotalInventory();
    int selectInputQuantity();
    Integer selectOutputQuantity();
    Integer selectOutputByStatus();
}