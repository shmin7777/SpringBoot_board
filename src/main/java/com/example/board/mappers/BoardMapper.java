package com.example.board.mappers;

import com.example.board.beans.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//    Persistence Tier - 영속 계층, 데이터 계층
//    데이터의 CRUD 연산을 수행하는 Mapper와 결과를 객체로 리턴하는 DAO까지 영속 계층에 속한다.

@Mapper
public interface BoardMapper {
//    게시글 목록
    public List<BoardVO> getList();
//    게시글 추가
    public void insert(BoardVO board);
//    게시글 추가(PK가져오기)
    public void insertSelectKey_bno(BoardVO board);
//    게시글 상세보기(특정 게시글 정보)
    public BoardVO read(Long bno);
//    게시글 수정
    public int update(BoardVO board);
//    게시글 삭제
    public int delete(Long bno);
}
