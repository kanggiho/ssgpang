package com.shinsegae.project.dashboard.mapper;

import com.shinsegae.project.dashboard.vo.AreaChartDTO;
import com.shinsegae.project.dashboard.vo.BarChartDTO;
import com.shinsegae.project.dashboard.vo.PieChartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashboardMapper {
    // 상단 카드 4개
    int selectTotalInventory();
    int selectInputQuantity();
    Integer selectOutputQuantity();
    Integer selectOutputByStatus();

    //pie chart
    List<PieChartDTO> selectPieChartData();

    //Area chart 입고량
    List<AreaChartDTO> selectAreaChartInputData();

    //Area chart 출고량
    List<AreaChartDTO> selectAreaChartOutputData();

    //Bar chart
    List<BarChartDTO> selectBarChartData();



}