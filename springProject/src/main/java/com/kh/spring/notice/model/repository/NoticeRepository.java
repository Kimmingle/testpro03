package com.kh.spring.notice.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.notice.model.vo.Notice;

@Repository
public class NoticeRepository {

	public int noticeCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("noticeMapper.noticeCount");
	}

	public List<Notice> findAll(SqlSessionTemplate sqlSession, Map<String, Integer> map) {
		return sqlSession.selectList("noticeMapper.findAll", map);
	}

	public int searchCount(SqlSessionTemplate sqlSession, Map<String, String> map) {
		return sqlSession.selectOne("noticeMapper.searchCount", map);
	}

	public List<Notice> findByConditionAndKetword(SqlSessionTemplate sqlSession, Map<String, String> map,
			RowBounds rowBounds) {
		return sqlSession.selectList("noticeMapper.findByConditionAndKetword");
	}

	public int insertNoti(SqlSessionTemplate sqlSession, Notice notice) {
		return sqlSession.insert("noticeMapper.insertNoti", notice);
	}

	public int increadeCount(SqlSessionTemplate sqlSession, int noticeNo) {
		return 0;
	}

	public Notice findbyId(SqlSessionTemplate sqlSession, int noticeNo) {
		return null;
	}

	public int delete(SqlSessionTemplate sqlSession,int noticeNo) {
		return 0;
	}



}