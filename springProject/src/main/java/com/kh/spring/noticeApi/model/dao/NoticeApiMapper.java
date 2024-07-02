package com.kh.spring.noticeApi.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.noticeApi.model.vo.Notice;

@Mapper
public interface NoticeApiMapper {
//repository를 마이바티스가 대신해준다?
	
	List<Notice> findAll();
	
	Notice findById(int noticeNo);
	
	int save(Notice notice);
	
	int update(Notice notice);
	
	int delete(int noticeNo);
}
