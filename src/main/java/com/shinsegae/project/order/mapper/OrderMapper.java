package com.shinsegae.project.order.mapper;

import com.shinsegae.project.order.vo.OrderManagementDTO;
import com.shinsegae.project.order.vo.OutputVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OutputVO> selectOutputAll();


    List<OrderManagementDTO> selectOrderManagementAll();
}