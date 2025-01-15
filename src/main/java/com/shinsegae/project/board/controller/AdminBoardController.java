package com.shinsegae.project.board.controller;

import com.shinsegae.project.board.service.BoardService;
import com.shinsegae.project.board.vo.BoardVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/board")
@RequiredArgsConstructor
@Tag(name = "게시판")
public class AdminBoardController {
    private final BoardService boardService;

    @GetMapping("board")
    @Operation(summary = "게시판 페이지", description = "관리자가 모든 게시글 목록을 조회할 수 있는 페이지를 반환")
    public String board(Model model) {
        List<BoardVO> list = boardService.selectBoardAll();
        model.addAttribute("list", list);
        return "admin/board/board";
    }


    @GetMapping("create")
    @Operation(summary = "게시글 등록", description = "게시글 작성 페이지를 반환")
    public String create(Model model, HttpSession session) {
        String adminId = session.getAttribute("adminId").toString();
        model.addAttribute("adminId", adminId);
        return "admin/board/create";
    }


    @PostMapping("create2")
    @Operation(summary = "게시글 작성", description = "입력된 데이터를 기반으로 새로운 게시글을 생성")
    public String create2(BoardVO boardVO) {
        boardService.insertBoard(boardVO);
        return "redirect:/admin/board/board";
    }

    @GetMapping("read")
    @Operation(summary = "게시판 조회", description = "게시글 번호를 통해 특정 게시글의 상세 내용을 조회")
    public String read(int no, Model model, HttpSession session) {
        String adminId = (String) session.getAttribute("adminId");
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("adminId", adminId);
        model.addAttribute("userId", userId);
        BoardVO boardVO = boardService.selectBoardByNo(no);
        model.addAttribute("boardVO", boardVO);
        return "admin/board/read";
    }

    @GetMapping("find")
    @Operation(summary = "게시글 제목 조건 검색", description = "입력된 키워드를 통해 게시글 제목을 조건 검색")
    public String find(String find, Model model) {
        List<BoardVO> list = boardService.getBoardByTitle(find);
        model.addAttribute("list", list);
        return "admin/board/board";
    }

    @GetMapping("update")
    @Operation(summary = "게시글 수정", description = "수정할 게시글 정보를 조회하여 수정 페이지를 반환")
    public String update(int no, Model model) {
        BoardVO boardVO = boardService.selectBoardByNo(no);
        model.addAttribute("boardVO", boardVO);
        return "admin/board/update";
    }

    @PostMapping("update2")
    @Operation(summary = "게시글 조회 후 수정", description = "수정된 데이터를 기반으로 게시글 정보를 업데이트")
    public String update2(BoardVO boardVO, Model model) {
        boardService.updateBoard(boardVO);
        return "redirect:/admin/board/board";
    }

    @GetMapping("delete")
    @Operation(summary = "게시글 삭제", description = "게시글 번호를 기반으로 특정 게시글을 삭제")
    public String delete(int no) {
        boardService.deleteBoard(no);
        return "redirect:/admin/board/board";
    }
}
