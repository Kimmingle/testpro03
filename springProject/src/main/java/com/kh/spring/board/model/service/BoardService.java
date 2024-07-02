package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;


public interface BoardService {
	
	
	
	//게시글 전체 조회 + 페이징 처리 
	int boardCount(); 
	
	
	//게시글 목록조회
	List<Board> findAll(Map<String, Integer> map);   
	
	//게시글 검색 기능 
	int searchCount(Map<String, String> map);
	
	//검색 목록 조회
	List<Board> findByConditionAndKetword(Map<String, String>map, RowBounds rowBounds);
	
	//게시글 작성 기능 
	//update, delete, insert는 기본적으로 반환타입이 int
	int insert(Board board);
	
	//게시글 상세보기
	//조회수 증가
	int increaseCount(int boardNo);
	//한 행을 조회해야하니까 Board로 반환
	//조회수랑 같이 움직여야함 - Trangection처리
	
	Board findById(int boardNo);
	
	//게시글 삭제하기
	int delete(int boardNo);


	int update(Board board);

	
	//사진 게시글 목록
	List<Board> selectImg();

	//ajax를 활용한 댓글 목록조회
	List<Reply> selectReply(int boardNo);

	//댓글 작성
	int insertReply(Reply reply);

	//board랑 board에 딸린 reply랑 같이 조회할거임ㅋㅋ;
	Board boardAndReply(int boardNo);

	//다섯개의 개시글 정보
	List<Board> findTopBoard();






	
	
	//게시글 수정하기
	
	
	//------------댓글 관련
	//ajax 활용한 댓글 목록 조회 
	
	//mybatis를 이용한 댓글 조회
	
	//댓글 작성하기 
	
	//-------------------- top-n
	
}
