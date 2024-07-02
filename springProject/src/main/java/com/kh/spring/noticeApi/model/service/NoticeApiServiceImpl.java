package com.kh.spring.noticeApi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.spring.noticeApi.model.dao.NoticeApiMapper;
import com.kh.spring.noticeApi.model.vo.Notice;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class NoticeApiServiceImpl implements NoticeApiService{

	private final NoticeApiMapper noticeApiMapper;
	
	@Override
	public List<Notice> findAll() {
		return noticeApiMapper.findAll();
	}

	@Override
	public Notice findById(int noticeNo) {
		return noticeApiMapper.findById(noticeNo);
	}

	@Override
	public int save(Notice notice) {
		return noticeApiMapper.save(notice);
	}

	@Override
	public int update(Notice notice) {
		return noticeApiMapper.update(notice);
	}

	@Override
	public int delete(int noticeNo) {
		return noticeApiMapper.delete(noticeNo);
	}


}
