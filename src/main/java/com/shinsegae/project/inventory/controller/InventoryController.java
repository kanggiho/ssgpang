package com.shinsegae.project.inventory.controller;

import com.shinsegae.project.inventory.service.InventoryService;
import com.shinsegae.project.inventory.vo.InventoryManagementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("admin/inventory")
@RequiredArgsConstructor
@Tag(name = "재고관리")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("confirm_inventory_list")
    @Operation(summary = "재고 확인 리스트", description = "재고 데이터와 합계를 조회하여 재고 확인 리스트 페이지를 반환")
    public String confirm_inventory_list(Model model) {
        List<String> classifications = inventoryService.getClassification();
        List<String> products = inventoryService.getProduct();
        List<String> warehouses = inventoryService.getWarehouse();
        List<String> manufacturers = inventoryService.getManufacturer();

        model.addAttribute("classifications", classifications);
        model.addAttribute("products", products);
        model.addAttribute("warehouses", warehouses);
        model.addAttribute("manufacturers", manufacturers);

        model.addAttribute("confirm_inventory_list", inventoryService.selectInventoryAll());
        return "admin/inventory/confirm_inventory_list";
    }

    @GetMapping("edit_inventory")
    @Operation(summary = "재고 편집 페이지", description = "재고를 수정하기 위한 편집 페이지를 반환")
    public String edit_inventory(Model model) {
        List<String> classifications = inventoryService.getClassification();
        List<String> products = inventoryService.getProduct();
        List<String> warehouses = inventoryService.getWarehouse();
        List<String> manufacturers = inventoryService.getManufacturer();

        model.addAttribute("classifications", classifications);
        model.addAttribute("products", products);
        model.addAttribute("warehouses", warehouses);
        model.addAttribute("manufacturers", manufacturers);
        return "admin/inventory/edit_inventory";
    }

    @DeleteMapping("delete/{code}")
    @ResponseBody
    @Operation(summary = "재고 삭제", description = "지정된 코드의 재고 데이터를 삭제")
    public boolean deleteInventory(@PathVariable("code") String code) {
        System.out.println(code);
        boolean result = inventoryService.deleteInventory(code);  // 삭제 서비스 호출
    return result;
   }

    // 수량 업데이트 API
    @PutMapping("/update/{code}")
    @Operation(summary = "재고 수량 업데이트", description = "지정된 코드의 재고 수량을 업데이트")
    public ResponseEntity<Boolean> updateStock(@PathVariable("code") String code, @RequestBody Map<String, Integer> request) {
        int stock = request.get("stock");
        boolean result = inventoryService.updateStock(code, stock);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    @Operation(summary = "재고 추가", description = "새로운 재고 데이터를 추가")
    public ResponseEntity<String> addStock(@RequestBody InventoryManagementDTO inventoryManagementDTO) {
        inventoryService.insertInventory(inventoryManagementDTO);
        return ResponseEntity.ok("Stock added successfully");
    }

}