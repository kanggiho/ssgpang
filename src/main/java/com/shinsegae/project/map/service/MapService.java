package com.shinsegae.project.map.service;

import com.shinsegae.project.map.vo.WarehouseInfoDTO;
import com.shinsegae.project.map.mapper.MapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MapService {
    private final MapMapper mapMapper;

    public List<Map<String, Object>> getWarehouseData() {
        List<WarehouseInfoDTO> warehouses = mapMapper.getWarehouseData();
        if (warehouses == null || warehouses.isEmpty()) {
            System.out.println("No data fetched from database");
            return new ArrayList<>();
        }

        List<Map<String, Object>> warehouseData = new ArrayList<>();
        for (WarehouseInfoDTO warehouse : warehouses) {
            System.out.println("Fetched warehouse: " + warehouse); // 디버깅 로그 추가

            String[] splitLocation = warehouse.getWarehouseLocation().split(",");
            double latitude = Double.parseDouble(splitLocation[0].trim());
            double longitude = Double.parseDouble(splitLocation[1].trim());

            Map<String, Object> data = new HashMap<>();
            data.put("name", warehouse.getWarehouseName());
            data.put("latitude", latitude);
            data.put("longitude", longitude);
            data.put("date", warehouse.getReleaseDate());
            warehouseData.add(data);
        }

        return warehouseData;
    }

}
