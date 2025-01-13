package com.shinsegae.project.order.mapper;

import com.shinsegae.project.order.vo.OrderInventoryManagementDTO;
import com.shinsegae.project.order.vo.OrderInventoryUpdateVO;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import com.shinsegae.project.order.vo.OutputVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    // 발주하기
    List<OrderInventoryManagementDTO> selectOrderInventoryManagementAll();
    void insertOutput(OutputVO outputVO);

    void updateInventory(OrderInventoryUpdateVO updateVO);

    //유저 uid찾기
    int findUserUid(String id);


    // 발주내역확인
    List<OutputVO> selectOutputAll();
    List<OrderManagementDTO> selectOrderManagementAll();


    // 발주요청관리

    /**
     * 승인 처리
     * @param outputId     주문 ID
     * @param confirmNum  승인번호 (6자리 난수)
     * @param confirmId   승인자 ID
     * @param status      상태 ("승인")
     */
    void updateApproval(@Param("outputId") int outputId,
                        @Param("confirmNum") int confirmNum,
                        @Param("confirmId") int confirmId,
                        @Param("status") String status);

    /**
     * 거절 처리
     * @param outputId 주문 ID
     * @param status  상태 ("거절")
     */
    void updateStatus(@Param("outputId") int outputId,
                      @Param("status") String status);



}