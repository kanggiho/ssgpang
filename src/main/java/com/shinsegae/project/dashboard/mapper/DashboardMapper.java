package com.shinsegae.project.dashboard.mapper;

import com.shinsegae.project.dashboard.vo.OutputVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashboardMapper {
    List<OutputVO> selectOutputAll();
}