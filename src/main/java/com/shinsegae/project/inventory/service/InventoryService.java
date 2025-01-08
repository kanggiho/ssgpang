package com.shinsegae.project.inventory.service;

import com.shinsegae.project.inventory.mapper.InventoryMapper;
import com.shinsegae.project.inventory.vo.InventoryManagementDTO;
import com.shinsegae.project.inventory.vo.InventoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryMapper inventoryMapper;

    public List<InventoryManagementDTO> selectInventoryAll() {
        return inventoryMapper.selectInventoryAll();
    }

    public List<InventoryManagementDTO> getInventoryByContent() {
        return inventoryMapper.getInventoryByContent();
    }

    public int updateInventory(InventoryManagementDTO inventoryManagementDTO) {
        return inventoryMapper.updateInventory(inventoryManagementDTO);
    }

    public int insertInventory(InventoryManagementDTO inventoryManagementDTO) {
        return inventoryMapper.insertInventory(inventoryManagementDTO);
    }

    public int deleteInventory(int product_code) {
        return inventoryMapper.deleteInventory(product_code);
    }

}