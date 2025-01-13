package com.shinsegae.project.board.controller;

import com.shinsegae.project.board.service.BoardService;
import com.shinsegae.project.board.vo.BoardVO;
import com.shinsegae.project.dashboard.service.DashboardService;
import com.shinsegae.project.dashboard.vo.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("board")
    public String board(Model model){
        List<BoardVO> list = boardService.selectBoardAll();
        model.addAttribute("list",list);
        return "admin/board/board";
    }

    //게시글 등록
    @GetMapping ("create")
    public String create() {
        return "admin/board/create";
    }

    @PostMapping("create2")
    public String create2(BoardVO boardVO) {
        boardService.insertBoard(boardVO);
        return "redirect:/admin/board/board";
    }

    //게시글 번호로 조회
    @GetMapping("read")
    public String read(int no , Model model){
        BoardVO boardVO = boardService.selectBoardByNo(no);
        model.addAttribute("boardVO",boardVO);
        return "admin/board/read"; //게시글 상세보기 페이지로
    }

    //게시글 제목 조건 검색
    @GetMapping("find")
    public String find(String find, Model model){
        List<BoardVO> list = boardService.getBoardByContent(find);
        model.addAttribute("list",list);
        return "admin/board/board";
    }

    //게시글 수정
    //게시글 조회 후 수정
    @GetMapping("update")
    public String update(int no , Model model){
        BoardVO boardVO = boardService.selectBoardByNo(no);
        model.addAttribute("boardVO", boardVO);
        return "admin/board/update";
    }

    @PostMapping("update2")
    public String update2(BoardVO boardVO, Model model){
        boardService.updateBoard(boardVO);
        return "redirect:/admin/board/board";
    }

    //게시글 삭제
    @GetMapping("delete")
    public String delete(int no){
        boardService.deleteBoard(no);
        return "redirect:/admin/board/board";
    }
}