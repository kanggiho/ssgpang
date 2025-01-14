package com.shinsegae.project.excel.mapper;

import com.shinsegae.project.excel.vo.ExcelInputVO;
import com.shinsegae.project.excel.vo.ExcelInventoryVO;
import com.shinsegae.project.excel.vo.ExcelOutputVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExcelMapper {

    //========================================내보내기 기능========================================

    List<ExcelInputVO> findAllInput();

    List<ExcelInventoryVO> findAllInventory();

    List<ExcelOutputVO> findAllOutput();


    //========================================가져오기 기능========================================

    // 모든 데이터 삭제
    void deleteInputData();

    void deleteInventoryData();

    void deleteOutputData();

    // 새 데이터 삽입
    void insertExcelInputData(@Param("inputList") List<ExcelInputVO> inputList);

    void insertExcelInventoryData(@Param("inventoryList") List<ExcelInventoryVO> inventoryList);

    void insertExcelOutputData(@Param("outputList") List<ExcelOutputVO> outputList);



}