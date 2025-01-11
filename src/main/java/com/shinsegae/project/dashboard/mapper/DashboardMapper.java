package com.shinsegae.project.dashboard.mapper;

import com.shinsegae.project.dashboard.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashboardMapper {
    //사용자 대시보드
    //상단 카드: 발주 승인대기, 승인완료, 승인거절, 총발주건수, 총 발주비용
    Integer selectOutputByStatusWaiting();
    Integer selectOutputByStatusApproval();
    Integer selectOutputByStatusReject();
    int selectTotalOutputQuantity();
    int selectTotalOutputPrice();

    //bar chart: 월별 발주량, 발주금액
    List<UserBarChartQuantityDTO> selectUserOutputQuantity();
    List<UserBarChartPriceDTO> selectUserOutputPrice();



    //pie chart: 나의 발주정보
    List<UserPieChartDTO> selectUserPieChartData();


    //관리자 대시보드
    //상단 카드: 총재고량, 입고수량, 출고수량, 발주 승인요청
    int selectTotalInventory();
    Integer selectInputQuantity();
    Integer selectOutputQuantity();
    Integer selectOutputByStatus();

    //area chart: 월별 입고량,출고량
    List<AreaChartInputDTO> selectAreaChartInputData();
    List<AreaChartOutputDTO> selectAreaChartOutputData();

    //table: 오늘의 입고 상품
    List<TodayInputDTO> selectTodayInputProduct();

    //bar chart: 지점별 출고현황
    List<BarChartDTO> selectBarChartData();
}