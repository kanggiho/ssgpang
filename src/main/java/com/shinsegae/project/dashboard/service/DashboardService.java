package com.shinsegae.project.dashboard.service;

import com.shinsegae.project.dashboard.mapper.DashboardMapper;
import com.shinsegae.project.dashboard.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardMapper dashboardMapper;
    //관리자
    //1. 총 재고량
    public int selectTotalInventory(){
        return dashboardMapper.selectTotalInventory();
    }
    //2. 총 입고량
    public Integer selectInputQuantity(){
        Integer result = dashboardMapper.selectInputQuantity();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }
    //3. 총 출고량
    public Integer selectOutputQuantity(){
        Integer result = dashboardMapper.selectOutputQuantity();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }
    //4. 승인대기 중 발주요청
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

    //사용자
    //1. 승인대기 중 발주요청
    public Integer selectOutputByStatusWaiting(){
        return dashboardMapper.selectOutputByStatusWaiting();
    }
    //2. 승인완료된 발주요청
    public Integer selectOutputByStatusApproval(){
        return dashboardMapper.selectOutputByStatusApproval();
    }
    //3. 승인거절된 발주요청
    public Integer selectOutputByStatusReject(){
        return dashboardMapper.selectOutputByStatusReject();
    }
    //4. 이번달 총 발주량
    public int selectTotalOutputQuantity(){
        return dashboardMapper.selectTotalOutputQuantity();

    }
    //5. 이번달 총 발주비용
    public int selectTotalOutputPrice(){
        return dashboardMapper.selectTotalOutputPrice();
    }

    //bar chart - 월별 발주량
    public List<UserBarChartQuantityDTO> selectUserOutputQuantity(){
        return dashboardMapper.selectUserOutputQuantity();
    }
    //bar chart - 월별 발주금액
    public List<UserBarChartPriceDTO> selectUserOutputPrice(){
        return dashboardMapper.selectUserOutputPrice();
    }

    //pie chart - 사용자의 발주 품목 top5
    public List<UserPieChartDTO> selectUserPieChartData(){
        return dashboardMapper.selectUserPieChartData();
    }
}

