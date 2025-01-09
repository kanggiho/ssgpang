package com.shinsegae.project.inventory.controller;

import com.shinsegae.project.inventory.service.InventoryService;
import com.shinsegae.project.inventory.vo.InventoryManagementDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("admin/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("confirm_inventory_list")
    public String confirm_inventory_list(Model model) {

        List<InventoryManagementDTO> list = inventoryService.selectInventoryAll();
        Map<String, Integer> sumList = inventoryService.getSum(list);
        model.addAttribute("confirm_inventory_list", inventoryService.selectInventoryAll());
        model.addAttribute("sumList", sumList);
        //모델로 전달(sumList)
        //${sumList.sum1} 출력
        return "admin/inventory/confirm_inventory_list";
    }

    @GetMapping("edit_inventory")
    public String edit_inventory() {
        return "admin/inventory/edit_inventory";
    }

}