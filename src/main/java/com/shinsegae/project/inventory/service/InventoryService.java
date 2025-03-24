package com.shinsegae.project.inventory.service;

import com.shinsegae.project.inventory.mapper.InventoryMapper;
import com.shinsegae.project.inventory.vo.InventoryManagementDTO;
import com.shinsegae.project.inventory.vo.InventoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    private final InventoryMapper inventoryMapper;

    // 재고 조회
    public List<InventoryManagementDTO> selectInventoryAll() {
        return inventoryMapper.selectInventoryAll();
    }

    // 수량 업데이트
    public boolean updateStock(String code, int stock) {
        try {
            inventoryMapper.updateStock(code, stock);  // Mapper의 updateStock 메서드 호출
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 새로운 재고 추가
    public int insertInventory(InventoryManagementDTO inventoryManagementDTO) {
        return inventoryMapper.insertInventory(inventoryManagementDTO);
    }

    // 재고 삭제
    public boolean deleteInventory(String code) {
        try {
            inventoryMapper.deleteInventory(code);  // Mapper의 deleteInventory 메서드 호출
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //드롭다운값: 품목 조회
    public List<String> getClassification() {
        return inventoryMapper.getClassification();
    }

    //드롭다운값: 자재 조회
    public List<String> getProduct() {
        return inventoryMapper.getProduct();
    }

    //드롭다운값: 제조업체 조회
    public List<String> getManufacturer() {
        return inventoryMapper.getManufacturer();
    }

    //드롭다운값: 창고 조회
    public List<String> getWarehouse() {
        return inventoryMapper.getWarehouse();
    }

}