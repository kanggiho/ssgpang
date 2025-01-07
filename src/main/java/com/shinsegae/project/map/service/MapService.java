package com.shinsegae.project.map.service;


import com.shinsegae.project.common.dataset.vo.WarehouseVO;
import com.shinsegae.project.map.mapper.MapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MapService {
    private final MapMapper mapMapper;

    public WarehouseVO getWarehouseLocations() {
        return mapMapper.getWarehouseLocations();
    }
}
