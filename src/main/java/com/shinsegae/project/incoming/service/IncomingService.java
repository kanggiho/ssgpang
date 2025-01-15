package com.shinsegae.project.incoming.service;

import com.shinsegae.project.incoming.mapper.IncomingMapper;
import com.shinsegae.project.incoming.vo.IncomingInputVO;
import com.shinsegae.project.incoming.vo.IncomingInventoryVO;
import com.shinsegae.project.incoming.vo.IncomingManagementDTO;
import com.shinsegae.project.incoming.vo.IncomingRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomingService {
    private final IncomingMapper incomingMapper;

    public List<IncomingManagementDTO> read() {
        return incomingMapper.selectInputAll();
    }
    public List<IncomingRequestDTO> insert() {
        return incomingMapper.selectRequestAll();
    }

    public void saveIncomingRequest(IncomingInputVO incomingInputVO) {
        // input 테이블에 먼저 삽입
        incomingMapper.insertIncomingRequest(incomingInputVO);

        // inventory 테이블에 삽입할 VO 생성
        IncomingInventoryVO inventoryVO = IncomingInventoryVO.builder()
                .code(incomingInputVO.getCode())
                .product_code(incomingInputVO.getProductCode())
                .manufacturer_code(incomingInputVO.getManufacturerCode())
                .warehouse_id(incomingInputVO.getWarehouseId())
                .price(incomingInputVO.getPrice())
                .stock(incomingInputVO.getWarehousedQuantity())
                .build();

        // inventory 테이블에 삽입
        incomingMapper.insertInventory(inventoryVO);
    }
}
