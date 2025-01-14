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
    public Integer selectOutputByStatusWaiting(String userId){
        return dashboardMapper.selectOutputByStatusWaiting(userId);
    }
    //승인완료
    public Integer selectOutputByStatusApproval(String userId){
        return dashboardMapper.selectOutputByStatusApproval(userId);
    }
    //승인거절
    public Integer selectOutputByStatusReject(String userId){
        return dashboardMapper.selectOutputByStatusReject(userId);
    }
    //총 발주건수
    public Integer selectTotalOutputQuantity(String userId){
        Integer result = dashboardMapper.selectTotalOutputQuantity(userId);
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    //bar chart: 월별 발주량
    public List<UserBarChartQuantityDTO> selectUserOutputQuantity(String userId){
        return dashboardMapper.selectUserOutputQuantity(userId);
    }
    //bar chart: 월별 발주금액
    public List<UserBarChartPriceDTO> selectUserOutputPrice(String userId){
        return dashboardMapper.selectUserOutputPrice(userId);
    }

    //pie chart: 나의 발주정보
    public List<UserPieChartDTO> selectUserPieChartData(String userId){
        return dashboardMapper.selectUserPieChartData(userId);
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
        Integer result = dashboardMapper.selectOutputByStatus();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

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

