package com.shinsegae.project.inventory.vo;

public class InventoryManagementDTO {
    private String itemClassification;
    private String productName;
    private String warehouseName;
    private String manufacturerName;
    private int price;
    private int stock;

    public InventoryManagementDTO() {
    }

    public InventoryManagementDTO(String itemClassification, String productName, String warehouseName, String manufacturerName, int price, int stock) {
        this.itemClassification = itemClassification;
        this.productName = productName;
        this.warehouseName = warehouseName;
        this.manufacturerName = manufacturerName;
        this.price = price;
        this.stock = stock;
    }

    public String getItemClassification() {
        return itemClassification;
    }

    public void setItemClassification(String itemClassification) {
        this.itemClassification = itemClassification;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
