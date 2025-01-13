package com.shinsegae.project.incoming.controller;

import com.shinsegae.project.incoming.service.IncomingService;
import com.shinsegae.project.incoming.vo.IncomingInputVO;
import com.shinsegae.project.incoming.vo.IncomingManagementDTO;
import com.shinsegae.project.incoming.vo.IncomingRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/incoming")
public class IncomingController {

    private final IncomingService incomingService;

    public IncomingController(IncomingService incomingService) {
        this.incomingService = incomingService;
    }

    @GetMapping("do_incoming")
    public String do_incoming(Model model) {
        List<IncomingRequestDTO> incomingRequest = incomingService.insert();
        model.addAttribute("incomingRequest", incomingRequest);

        return "admin/incoming/do_incoming";
    }

    @GetMapping("confirm_incoming_list")
    public String confirm_incoming_list(Model model) {
        List<IncomingManagementDTO> incomingList = incomingService.read();
        model.addAttribute("incomingList", incomingList);
        return "admin/incoming/confirm_incoming_list";
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveIncoming(@RequestBody IncomingInputVO incomingInputVO) {
        // Service 통해 DB에 Insert
        incomingService.saveIncomingRequest(incomingInputVO);

        // 성공 시 OK 응답
        return ResponseEntity.ok("입고 신청 완료");
    }

}