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
    //사용자 대시보드
    //발주 승인대기
    public Integer selectOutputByStatusWaiting(){
        return dashboardMapper.selectOutputByStatusWaiting();
    }
    //승인완료
    public Integer selectOutputByStatusApproval(){
        return dashboardMapper.selectOutputByStatusApproval();
    }
    //승인거절
    public Integer selectOutputByStatusReject(){
        return dashboardMapper.selectOutputByStatusReject();
    }
    //총 발주건수
    public int selectTotalOutputQuantity(){
        return dashboardMapper.selectTotalOutputQuantity();

    }
    //총 발주비용
    public int selectTotalOutputPrice(){
        return dashboardMapper.selectTotalOutputPrice();
    }

    //bar chart: 월별 발주량
    public List<UserBarChartQuantityDTO> selectUserOutputQuantity(){
        return dashboardMapper.selectUserOutputQuantity();
    }
    //bar chart: 월별 발주금액
    public List<UserBarChartPriceDTO> selectUserOutputPrice(){
        return dashboardMapper.selectUserOutputPrice();
    }

    //pie chart: 나의 발주정보
    public List<UserPieChartDTO> selectUserPieChartData(){
        return dashboardMapper.selectUserPieChartData();
    }

    //관리자 대시보드
    //총 재고량
    public int selectTotalInventory(){
        return dashboardMapper.selectTotalInventory();
    }
    //입고수량
    public Integer selectInputQuantity(){
        Integer result = dashboardMapper.selectInputQuantity();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }
    //출고수량
    public Integer selectOutputQuantity(){
        Integer result = dashboardMapper.selectOutputQuantity();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }
    //발주 승인요청
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
//    public List<PieChartDTO> selectPieChartData(){
//        return dashboardMapper.selectPieChartData();
//    }

    //area chart: 월별 입고량
    public List<AreaChartInputDTO> selectAreaChartInputData(){
        return dashboardMapper.selectAreaChartInputData();
    }
    //area chart: 월별 출고량
    public List<AreaChartOutputDTO> selectAreaChartOutputData(){
        return dashboardMapper.selectAreaChartOutputData();
    }
    //table: 오늘의 입고 상품
    public List<TodayInputDTO> selectTodayInputProduct(){
        return dashboardMapper.selectTodayInputProduct();
    }
    //bar chart: 지점별 출고현황
    public List<BarChartDTO> selectBarChartData(){
        return dashboardMapper.selectBarChartData();
    }
}

