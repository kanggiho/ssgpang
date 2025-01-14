package com.shinsegae.project.excel.service;

import com.shinsegae.project.excel.mapper.ExcelMapper;
import com.shinsegae.project.excel.vo.ExcelInputVO;
import com.shinsegae.project.excel.vo.ExcelInventoryVO;
import com.shinsegae.project.excel.vo.ExcelOutputVO;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final ExcelMapper excelMapper;


    //========================================내보내기 기능========================================


    public ByteArrayInputStream exportDataToExcel(String dataType, String fileName) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            switch (dataType) {
                case "input":
                    createInputSheet(workbook);
                    break;
                case "inventory":
                    createInventorySheet(workbook);
                    break;
                case "output":
                    createOutputSheet(workbook);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid data type: " + dataType);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private void createInputSheet(Workbook workbook) {
        List<ExcelInputVO> inputList = excelMapper.findAllInput();
        String[] columns = {"Input Num", "Manufacturer Code", "Product Code", "Warehoused Quantity", "Warehoused Date"};

        Sheet sheet = workbook.createSheet("입고");
        createHeaderRow(workbook, sheet, columns);

        int rowIdx = 1;
        for (ExcelInputVO input : inputList) {
            if (input == null) continue;

            Row row = sheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(input.getInputNum());
            row.createCell(1).setCellValue(input.getManufacturerCode());
            row.createCell(2).setCellValue(input.getProductCode());
            row.createCell(3).setCellValue(input.getWarehousedQuantity());
            row.createCell(4).setCellValue(input.getWarehousedDate());
        }

        autoSizeColumns(sheet, columns.length);
    }

    private void createInventorySheet(Workbook workbook) {
        List<ExcelInventoryVO> inventoryList = excelMapper.findAllInventory();
        String[] columns = {"Code", "Product Code", "Manufacturer Code", "Warehouse ID", "Price", "Stock"};

        Sheet sheet = workbook.createSheet("재고");
        createHeaderRow(workbook, sheet, columns);

        int rowIdx = 1;
        for (ExcelInventoryVO inventory : inventoryList) {
            if (inventory == null) continue;

            Row row = sheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(inventory.getCode());
            row.createCell(1).setCellValue(inventory.getProductCode());
            row.createCell(2).setCellValue(inventory.getManufacturerCode());
            row.createCell(3).setCellValue(inventory.getWarehouseId());
            row.createCell(4).setCellValue(inventory.getPrice());
            row.createCell(5).setCellValue(inventory.getStock());
        }

        autoSizeColumns(sheet, columns.length);
    }

    private void createOutputSheet(Workbook workbook) {
        List<ExcelOutputVO> outputList = excelMapper.findAllOutput();
        String[] columns = {"Output ID", "Product Code", "Warehouse ID", "User ID", "Confirm Num", "Confirm ID", "Status", "Unit Price", "Release Quantity", "Release Date"};

        Sheet sheet = workbook.createSheet("발주");
        createHeaderRow(workbook, sheet, columns);

        int rowIdx = 1;
        for (ExcelOutputVO output : outputList) {
            if (output == null) continue;

            Row row = sheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(output.getOutputId());
            row.createCell(1).setCellValue(output.getProductCode());
            row.createCell(2).setCellValue(output.getWarehouseId());
            row.createCell(3).setCellValue(output.getUserId());
            row.createCell(4).setCellValue(output.getConfirmNum());
            row.createCell(5).setCellValue(output.getConfirmId());
            row.createCell(6).setCellValue(output.getStatus());
            row.createCell(7).setCellValue(output.getUnitPrice());
            row.createCell(8).setCellValue(output.getReleaseQuantity());
            row.createCell(9).setCellValue(output.getReleaseDate());
        }

        autoSizeColumns(sheet, columns.length);
    }

    private void createHeaderRow(Workbook workbook, Sheet sheet, String[] columns) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);

        for (int col = 0; col < columns.length; col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(columns[col]);
            cell.setCellStyle(headerCellStyle);
        }
    }

    private void autoSizeColumns(Sheet sheet, int numberOfColumns) {
        for (int i = 0; i < numberOfColumns; i++) {
            sheet.autoSizeColumn(i);
        }
    }



    //========================================가져오기 기능========================================



    public void insertInputExcelData(InputStream inputStream) throws Exception {
        List<ExcelInputVO> inputList = new ArrayList<>();

        // 엑셀 파일 읽기
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                ExcelInputVO inputVO = ExcelInputVO.builder()
                        .inputNum((int) getCellValueAsNumeric(row.getCell(0))) // Numeric 처리
                        .manufacturerCode(getCellValueAsString(row.getCell(1))) // String 처리
                        .productCode((int) getCellValueAsNumeric(row.getCell(2)))
                        .warehousedQuantity((int) getCellValueAsNumeric(row.getCell(3)))
                        .warehousedDate(getCellValueAsString(row.getCell(4)))
                        .build();

                inputList.add(inputVO);
            }
        }

        // 테이블 데이터 초기화
        excelMapper.deleteInputData();

        // 새 데이터 삽입
        excelMapper.insertExcelInputData(inputList);
    }


    public void insertInventoryExcelData(InputStream inputStream) throws Exception {
        List<ExcelInventoryVO> inventoryList = new ArrayList<>();

        // 엑셀 파일 읽기
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                ExcelInventoryVO inventoryVO = ExcelInventoryVO.builder()
                        .code(getCellValueAsString(row.getCell(0))) // Numeric 처리
                        .productCode((int) getCellValueAsNumeric(row.getCell(1)))
                        .manufacturerCode(getCellValueAsString(row.getCell(2))) // String 처리
                        .warehouseId((int) getCellValueAsNumeric(row.getCell(3)))
                        .price((int) getCellValueAsNumeric(row.getCell(4)))
                        .stock((int) getCellValueAsNumeric(row.getCell(5)))
                        .build();

                inventoryList.add(inventoryVO);
            }
        }

        // 테이블 데이터 초기화
        excelMapper.deleteInventoryData();

        // 새 데이터 삽입
        excelMapper.insertExcelInventoryData(inventoryList);
    }

    public void insertOutputExcelData(InputStream inputStream) throws Exception {
        List<ExcelOutputVO> outputList = new ArrayList<>();

        // 엑셀 파일 읽기
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                ExcelOutputVO outputVO = ExcelOutputVO.builder()
                        .outputId((int) getCellValueAsNumeric(row.getCell(0))) // Numeric 처리
                        .productCode((int) getCellValueAsNumeric(row.getCell(1))) // Numeric 처리
                        .warehouseId((int) getCellValueAsNumeric(row.getCell(2)))
                        .userId((int) getCellValueAsNumeric(row.getCell(3)))
                        .confirmNum((int) getCellValueAsNumeric(row.getCell(4)))
                        .confirmId((int) getCellValueAsNumeric(row.getCell(5)))
                        .status(getCellValueAsString(row.getCell(6)))
                        .unitPrice((int) getCellValueAsNumeric(row.getCell(7)))
                        .releaseQuantity((int) getCellValueAsNumeric(row.getCell(8)))
                        .releaseDate(getCellValueAsString(row.getCell(9)))
                        .build();

                outputList.add(outputVO);
            }
        }

        // 테이블 데이터 초기화
        excelMapper.deleteOutputData();

        // 새 데이터 삽입
        excelMapper.insertExcelOutputData(outputList);
    }



    // 셀 값이 숫자인 경우 처리
    private double getCellValueAsNumeric(Cell cell) {
        if (cell == null) return 0;
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            return Double.parseDouble(cell.getStringCellValue());
        }
        return 0;
    }

    // 셀 값이 문자열인 경우 처리
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        }
        return "";
    }




















}
