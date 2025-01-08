package com.shinsegae.project.inventory.service;

import com.shinsegae.project.inventory.mapper.InventoryMapper;
import com.shinsegae.project.inventory.vo.InventoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryMapper inventoryMapper;

    public List<InventoryVO> read() {
        return inventoryMapper.selectInventoryAll();
    }

}