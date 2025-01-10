package com.shinsegae.project.dashboard.mapper;

import com.shinsegae.project.dashboard.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashboardMapper {
    //관리자
    //상단 카드 4개
    //1. 총 재고량
    int selectTotalInventory();
    //2. 총 입고량
    Integer selectInputQuantity();
    //3. 총 출고량
    Integer selectOutputQuantity();
    //4. 승인대기 중 발주요청
    Integer selectOutputByStatus();

    //pie chart
    List<PieChartDTO> selectPieChartData();

    //Area chart 입고량
    List<AreaChartInputDTO> selectAreaChartInputData();
    //Area chart 출고량
    List<AreaChartOutputDTO> selectAreaChartOutputData();

    //Bar chart
    List<BarChartDTO> selectBarChartData();

    //사용자
    //상단 카드 5개
    //1. 승인대기 중 발주요청
    Integer selectOutputByStatusWaiting();
    //2. 승인완료된 발주요청
    Integer selectOutputByStatusApproval();
    //3. 승인거절된 발주요청
    Integer selectOutputByStatusReject();
    //4. 이번달 총 발주량
    int selectTotalOutputQuantity();
    //5. 이번달 총 발주비용
    int selectTotalOutputPrice();

    //bar chart - 월별 발주량
    List<UserBarChartQuantityDTO> selectUserOutputQuantity();
    //bar chart - 월별 발주금액
    List<UserBarChartPriceDTO> selectUserOutputPrice();


    //pie chart - 사용자의 발주 품목 top5
    List<UserPieChartDTO> selectUserPieChartData();


}