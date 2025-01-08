package com.shinsegae.project.order.service;

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

    public List<OutputVO> read() {
        return orderMapper.selectOutputAll();
    }

    public List<OrderManagementDTO> selectOutputTable(){ return orderMapper.selectOrderManagementAll();}

}