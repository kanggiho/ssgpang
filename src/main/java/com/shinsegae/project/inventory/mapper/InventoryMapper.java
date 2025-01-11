package com.shinsegae.project.inventory.mapper;

import com.shinsegae.project.inventory.vo.InventoryManagementDTO;
import com.shinsegae.project.inventory.vo.InventoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface InventoryMapper {
    List<InventoryManagementDTO> selectInventoryAll();
    List<InventoryManagementDTO> getInventoryByContent();
    void updateStock(@Param("code") String code, @Param("stock") int stock);
    int insertInventory(InventoryManagementDTO inventoryManagementDTO);
    void deleteInventory(String code);
}