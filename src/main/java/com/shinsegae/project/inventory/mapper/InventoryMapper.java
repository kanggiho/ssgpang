package com.shinsegae.project.inventory.mapper;

import com.shinsegae.project.inventory.vo.InventoryManagementDTO;
import com.shinsegae.project.inventory.vo.InventoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryMapper {
    List<InventoryManagementDTO> selectInventoryAll();
    List<InventoryManagementDTO> getInventoryByContent();
    int updateInventory(InventoryManagementDTO inventoryManagementDTO);
    int insertInventory(InventoryManagementDTO inventoryManagementDTO);
    int deleteInventory(int product_code);
}