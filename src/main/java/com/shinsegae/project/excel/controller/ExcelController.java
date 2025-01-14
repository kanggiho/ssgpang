package com.shinsegae.project.excel.controller;

import com.shinsegae.project.excel.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/admin/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    //========================================내보내기 기능========================================

    @GetMapping("/do_extract")
    public String doExtractMain(){
        return "admin/excel/do_extract";
    }

    @PostMapping("/do_extract/extract")
    @ResponseBody
    public ResponseEntity<InputStreamResource> exportDataToExcel(
            @RequestParam("dataType") String dataType,
            @RequestParam("fileName") String fileName) {
        ByteArrayInputStream in;
        try {
            in = excelService.exportDataToExcel(dataType, fileName);
        } catch (IOException e) {
            // 로깅 추가 (예: Logger.error("엑셀 파일 생성 실패", e))
            return ResponseEntity.status(500).build();
        } catch (IllegalArgumentException e) {
            // 잘못된 데이터 유형 처리
            return ResponseEntity.badRequest().build();
        }

        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = URLEncoder.encode(fileName + ".xlsx", StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");
        headers.add("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

    //========================================가져오기 기능========================================

    @GetMapping("/do_insert")
    public String doInsertMain(){
        return "admin/excel/do_insert";
    }

    @PostMapping("/do_insert/insert")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("dataType") String dataType) {
        try {
            if(dataType.equals("input")){
                excelService.insertInputExcelData(file.getInputStream());
            }else if(dataType.equals("inventory")){
                excelService.insertInventoryExcelData(file.getInputStream());
            }else if(dataType.equals("output")){
                excelService.insertOutputExcelData(file.getInputStream());
            }

            return ResponseEntity.ok("엑셀 데이터가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("엑셀 데이터를 업데이트하는 중 오류가 발생했습니다.");
        }
    }

}
