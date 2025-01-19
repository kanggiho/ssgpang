package com.shinsegae.project.dashboard.mapper;

import com.shinsegae.project.dashboard.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashboardMapper {
    //사용자 대시보드
    //상단 카드: 발주 승인대기, 승인완료, 승인거절, 총발주건수
    Integer selectOutputByStatusWaiting(String id);
    Integer selectOutputByStatusApproval(String id);
    Integer selectOutputByStatusReject(String id);
    Integer selectTotalOutputQuantity(String id);

    //bar chart: 월별 발주량, 발주금액
    List<UserBarChartQuantityDTO> selectUserOutputQuantity(String userId);
    List<UserBarChartPriceDTO> selectUserOutputPrice(String userId);

    //pie chart: 나의 발주정보
    List<UserPieChartDTO> selectUserPieChartData(String userId);


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

    //bar chart: 사용자별 출고현황
    List<BarChartDTO> selectBarChartData();
}