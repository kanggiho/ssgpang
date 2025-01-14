package com.shinsegae.project.excel.mapper;

import com.shinsegae.project.excel.vo.ExcelInputVO;
import com.shinsegae.project.excel.vo.ExcelInventoryVO;
import com.shinsegae.project.excel.vo.ExcelOutputVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExcelMapper {

    List<ExcelInputVO> findAllInput();

    List<ExcelInventoryVO> findAllInventory();

    List<ExcelOutputVO> findAllOutput();
}