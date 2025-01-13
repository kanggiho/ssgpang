package com.shinsegae.project.order.mapper;

import com.shinsegae.project.order.vo.OrderInventoryManagementDTO;
import com.shinsegae.project.order.vo.OrderInventoryUpdateVO;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import com.shinsegae.project.order.vo.OutputVO;
import org.apache.ibatis.annotations.Mapper;

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
}