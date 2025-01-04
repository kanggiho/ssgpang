package com.shinsegae.project.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/inventory")
public class InventoryController {

    @GetMapping("confirm_inventory_list")
    public String confirm_inventory_list() {
        return "admin/inventory/confirm_inventory_list";
    }

    @GetMapping("edit_inventory")
    public String edit_inventory() {
        return "admin/inventory/edit_inventory";
    }

}