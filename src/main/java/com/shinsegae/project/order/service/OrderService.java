package com.shinsegae.project.order.service;

import com.shinsegae.project.order.vo.OrderInventoryManagementDTO;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import com.shinsegae.project.order.vo.OutputVO;
import com.shinsegae.project.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;


    //발주하기
    public List<OrderInventoryManagementDTO> selectOutputTable(){ return orderMapper.selectOrderInventoryManagementAll();}

    public void saveOutput(OutputVO outputVO) {
        orderMapper.insertOutput(outputVO);
    }


    //발주내역조회

    public List<OutputVO> read() {
        return orderMapper.selectOutputAll();
    }
    public List<OrderManagementDTO> selectOutputConfirmTable(){ return orderMapper.selectOrderManagementAll();}

}