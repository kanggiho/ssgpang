package com.shinsegae.project.map.controller;


import com.shinsegae.project.common.dataset.vo.WarehouseVO;
import com.shinsegae.project.map.mapper.MapMapper;
import com.shinsegae.project.map.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MapController {
    private final MapService mapService;


    @GetMapping("map")
    public String getWarehouse() {
        WarehouseVO warehouseVO = mapService.getWarehouseLocations();
        String location = warehouseVO.getWarehouse_location();

        return "index";
    }


}
