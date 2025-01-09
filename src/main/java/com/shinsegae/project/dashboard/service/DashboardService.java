package com.shinsegae.project.dashboard.service;

import com.shinsegae.project.dashboard.mapper.DashboardMapper;
import com.shinsegae.project.dashboard.vo.AreaChartInputDTO;
import com.shinsegae.project.dashboard.vo.AreaChartOutputDTO;
import com.shinsegae.project.dashboard.vo.BarChartDTO;
import com.shinsegae.project.dashboard.vo.PieChartDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardMapper dashboardMapper;

    public int selectTotalInventory(){
        return dashboardMapper.selectTotalInventory();
    }
    public int selectInputQuantity(){
        return dashboardMapper.selectInputQuantity();
    }
    public Integer selectOutputQuantity(){
        Integer result = dashboardMapper.selectOutputQuantity();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }
    public Integer selectOutputByStatus(){
        return dashboardMapper.selectOutputByStatus();
//        Integer result = dashboardMapper.selectOutputByStatus();
//        if (result == null) {
//            return 0;
//        } else {
//            return result;
//        }
    }

    //pie chart
    public List<PieChartDTO> selectPieChartData(){
        return dashboardMapper.selectPieChartData();
    }

    //Area chart 입고량
    public List<AreaChartInputDTO> selectAreaChartInputData(){
        return dashboardMapper.selectAreaChartInputData();

    }

    //Area chart 출고량
    public List<AreaChartOutputDTO> selectAreaChartOutputData(){
        return dashboardMapper.selectAreaChartOutputData();
    }

    //Bar chart
    public List<BarChartDTO> selectBarChartData(){
        return dashboardMapper.selectBarChartData();
    }

}

