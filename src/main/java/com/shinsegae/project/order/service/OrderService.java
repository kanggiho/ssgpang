package com.shinsegae.project.order.service;

import com.shinsegae.project.order.vo.*;
import com.shinsegae.project.order.mapper.OrderMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;


    //발주하기
    public List<OrderInventoryManagementDTO> selectOutputTable() {
        return orderMapper.selectOrderInventoryManagementAll();
    }

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

    public List<OrderManagementDTO> selectOutputConfirmTable() {
        return orderMapper.selectOrderManagementAll();
    }

    //발주요청관리

    /**
     * 승인 처리
     *
     * @param outputId 승인할 주문 ID
     * @param adminId  승인자 ID (현재 관리자)
     */
    public void approveOrder(int outputId, int adminId) {
        int confirmNum = Integer.parseInt(generateRandomNumber(6)); // 6자리 난수 생성
        orderMapper.updateApproval(outputId, confirmNum, adminId, "승인");
    }

    /**
     * 거절 처리
     *
     * @param outputId 거절할 주문 ID
     */
    public void rejectOrder(int outputId, int quantity, String name) {
        orderMapper.updateStatus(outputId, "거절");
        OrderInventoryUpdateVO updateVO = new OrderInventoryUpdateVO();
        updateVO.setChange(quantity);
        updateVO.setProductCode(orderMapper.findProductCode(name));
        orderMapper.updateAdminInventory(updateVO);
    }

    /**
     * 6자리 난수 생성
     *
     * @param length 생성할 난수 길이
     * @return 6자리 난수 문자열
     */
    private String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // 0~9
        }
        return sb.toString();
    }

    public void cancelOrder(String outputId, String productName, String releaseQuantity) {
        orderMapper.cancelOutput(Integer.parseInt(outputId));
        OrderInventoryUpdateVO updateVO = new OrderInventoryUpdateVO();
        updateVO.setChange(Integer.parseInt(releaseQuantity));
        updateVO.setProductCode(orderMapper.findProductCode(productName));
        orderMapper.updateAdminInventory(updateVO);
    }
}