package com.kh.spring.noticeApi.model.service;

import java.util.List;

import com.kh.spring.noticeApi.model.vo.Notice;



public interface NoticeApiService {
	
	List<Notice> findAll();
	
	Notice findById(int noticeNo);
	
	int save(Notice notice);
	
	int update(Notice notice);
	
	int delete(int noticeNo);




}
