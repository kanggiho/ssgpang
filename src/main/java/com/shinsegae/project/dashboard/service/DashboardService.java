package com.shinsegae.project.dashboard.service;

import com.shinsegae.project.dashboard.mapper.DashboardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

   private final DashboardMapper dashboardMapper;

    public int selectTotalInventory(){
        return dashboardMapper.selectTotalInventory();
    }
    public int selectInputQuantity(){
        return dashboardMapper.selectInputQuantity();
    }
    public Integer selectOutputQuantity(){
        Integer result = dashboardMapper.selectOutputQuantity();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }
    public Integer selectOutputByStatus(){
       return dashboardMapper.selectOutputByStatus();
//        Integer result = dashboardMapper.selectOutputByStatus();
//        if (result == null) {
//            return 0;
//        } else {
//            return result;
//        }
    }
}