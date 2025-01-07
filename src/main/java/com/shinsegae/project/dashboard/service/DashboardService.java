package com.shinsegae.project.dashboard.service;

import com.shinsegae.project.dashboard.mapper.DashboardMapper;
import com.shinsegae.project.dashboard.vo.OutputVO1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

   private final DashboardMapper dashboardMapper;

    public List<OutputVO1> read() {
        return dashboardMapper.selectOutput1All();
    }

}