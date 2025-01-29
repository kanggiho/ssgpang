package com.shinsegae.project.map.controller;

import com.shinsegae.project.map.service.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Tag(name = "맵 API")
public class MapController {
    private final MapService mapService;

    @GetMapping("admin/map/map")
    @Operation(summary = "위치 정보", description = "창고들의 위치 목록 조회")
    public String getWarehouse(Model model) {
        List<Map<String, Object>> warehouseData = mapService.getWarehouseData();
        model.addAttribute("warehouseData", warehouseData);
        return "admin/map/map";
    }

    @GetMapping("user/map/map")
    public String getWarehouse2(Model model) {
        List<Map<String, Object>> warehouseData = mapService.getWarehouseData();
        model.addAttribute("warehouseData", warehouseData);
        return "user/map/map";
    }
}
