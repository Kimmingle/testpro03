package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	int returnNum();
	
	//기획할때 
	//로그인, 회원가입, 수정, 삭제, 중복체크, 아이디 
	
	Member login(Member member);
	int insert(Member member);
	int update(Member member);
	int delete(String userId);

	
	
	//아이디 체크
	int idCheck(String checkId);
	
}
