package com.shinsegae.project.order.service;

import com.shinsegae.project.order.vo.OrderInventoryManagementDTO;
import com.shinsegae.project.order.vo.OrderInventoryUpdateVO;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import com.shinsegae.project.order.vo.OutputVO;
import com.shinsegae.project.order.mapper.OrderMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;


    //발주하기
    public List<OrderInventoryManagementDTO> selectOutputTable(){ return orderMapper.selectOrderInventoryManagementAll();}

    public void saveOutput(OutputVO outputVO, HttpSession session) {
        OrderInventoryUpdateVO orderInventoryUpdateVO = new OrderInventoryUpdateVO();
        orderInventoryUpdateVO.setChange(outputVO.getRelease_quantity());
        orderInventoryUpdateVO.setProductCode(outputVO.getProduct_code());
        int uid = orderMapper.findUserUid(session.getAttribute("userId").toString());
        outputVO.setUser_id(uid);
        orderMapper.insertOutput(outputVO);
        orderMapper.updateInventory(orderInventoryUpdateVO);
    }


    //발주내역조회

    public List<OutputVO> read() {
        return orderMapper.selectOutputAll();
    }
    public List<OrderManagementDTO> selectOutputConfirmTable(){ return orderMapper.selectOrderManagementAll();}

}