 package com.kh.spring.member.model.repository;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;


//Repository: 저장소
//영속성/지속성 작업 : 휘발되지않고 저장소에 저장되도록 하는거인듯 (DB crud 작업)
//즉 여기 있어야할건 DB관련 SQL코드 
//db랑 소통하는 애
@Repository
public class MemberRepository {
	
	//void 쓰면 안됨 - 반드시!! 여기서 뭐든 반환해서 controller까지 가야함(그래야 controller에서 응답화면 지정 가능함)
	public Member login(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.selectOne("memberMapper.login", member);
		
	}
	
	public int insert(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.insert("memberMapper.insert", member);
	}
	
	public int update(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.update("memberMapper.update", member);
	}

	public int delete(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.delete("memberMapper.delete", userId);
	}

	public int idCheck(SqlSessionTemplate sqlSession, String checkId) {
		return sqlSession.selectOne("memberMapper.idCheck", checkId);
	}
	
}
