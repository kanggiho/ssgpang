package com.shinsegae.project.board.mapper;

import com.shinsegae.project.board.vo.BoardVO;
import com.shinsegae.project.dashboard.vo.*;
import com.shinsegae.project.incoming.vo.InputVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    //게시글 등록
    int insertBoard(BoardVO boardVO);
    //게시글 전체 목록 조회
    List<BoardVO> selectBoardAll();
    //게시글 번호로 조회
    BoardVO selectBoardByNo(int no);
    //게시글 수정
    int updateBoard(BoardVO boardVO);
    //게시글 삭제
    int deleteBoard(int no);
    //게시글 내용 조건 검색
    List<BoardVO> getBoardByContent(String find);
}