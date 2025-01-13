package com.shinsegae.project.board.service;

import com.shinsegae.project.board.mapper.BoardMapper;
import com.shinsegae.project.board.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    //게시글 등록
    public int insertBoard(BoardVO boardVO){
        return boardMapper.insertBoard(boardVO);
    }
    //게시글 전체 목록 조회
    public List<BoardVO> selectBoardAll(){
        return boardMapper.selectBoardAll();
    }
    //게시글 번호로 조회
    public BoardVO selectBoardByNo(int no){
        return boardMapper.selectBoardByNo(no);
    }
    //게시글 수정
    public int updateBoard(BoardVO boardVO){
        return boardMapper.updateBoard(boardVO);
    }
    //게시글 삭제
    public int deleteBoard(int no){
        return boardMapper.deleteBoard(no);
    }
    //게시글 내용 조건 검색
    public List<BoardVO> getBoardByContent(String find){
        return boardMapper.getBoardByContent(find);
    }
}