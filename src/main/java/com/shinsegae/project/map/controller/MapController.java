package com.shinsegae.project.map.controller;

import com.shinsegae.project.map.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/map")
@RequiredArgsConstructor
public class MapController {
    private final MapService mapService;

    @GetMapping("map")
    public String getWarehouse(Model model) {
        List<Map<String, Object>> warehouseData = mapService.getWarehouseData();
        model.addAttribute("warehouseData", warehouseData);
        return "admin/map/map";
    }
}
