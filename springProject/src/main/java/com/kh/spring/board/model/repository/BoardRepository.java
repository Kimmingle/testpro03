package com.kh.spring.board.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;

@Repository
public class BoardRepository {

	public int boardCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.boardCount");  //그룹함수들은 항상 One인듯
	}

	public List<Board> findAll(SqlSessionTemplate sqlSession, Map<String, Integer> map) {
		return sqlSession.selectList("boardMapper.findAll", map);
		//rowBounds쓸때는 selectList의 매개변수 3개짜리로 꼭 서야함 빈건 null처리
	}

	public int searchCount(SqlSessionTemplate sqlSession, Map<String, String> map) {
		return sqlSession.selectOne("boardMapper.searchCount", map);
	}

	public List<Board>findByConditionAndKetword(SqlSessionTemplate sqlSession,  Map<String, String> map, RowBounds rowBounds) {
		return sqlSession.selectList("boardMapper.findByConditionAndKetword", map, rowBounds);
	}

	public int insert(SqlSessionTemplate sqlSession, Board board) {
		return sqlSession.insert("boardMapper.insert", board);
	}

	public int increaseCount(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}

	public Board findById(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.findById", boardNo);
	}

	public int delete(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.delete", boardNo);
	}

	public int update(SqlSessionTemplate sqlSession, Board board) {
		return sqlSession.update("boardMapper.update", board);
	}

	public List<Board> selectImg(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("boardMapper.selectImg");
	}

	public List<Reply> selectReply(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectList("boardMapper.selectReply", boardNo);
	}

	public int insertReply(SqlSessionTemplate sqlSession, Reply reply) {
		return sqlSession.insert("boardMapper.insertReply", reply);

	}

	public Board boardAndReply(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.boardAndReply", boardNo);
	}

	public List<Board> findTopBoard(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("boardMapper.findTopBoard");
	}

	
	
	

}
