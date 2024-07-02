package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.notice.model.vo.Notice;

public interface NoticeService {
	
	//게시글 전체 조회 + 페이징 처리 
		int noticeCount(); 
		
		
		//게시글 목록조회
		List<Notice> findAll(Map<String, Integer> map);   
		
		//게시글 검색 기능 
		int searchCount(Map<String, String> map);
		
		//검색 목록 조회
		List<Notice> findByConditionAndKetword(Map<String, String>map, RowBounds rowBounds);
		
		//게시글 작성 기능 
		//update, delete, insert는 기본적으로 반환타입이 int
		int insert(Notice notice);
		
		//게시글 상세보기
		//조회수 증가
		int increadeCount(int noticeNo);
		//한 행을 조회해야하니까 Board로 반환
		//조회수랑 같이 움직여야함 - Trangection처리
		Notice findbyId(int noticeNo);
		
		//게시글 삭제하기
		int delete(int noticeNo);
	
}