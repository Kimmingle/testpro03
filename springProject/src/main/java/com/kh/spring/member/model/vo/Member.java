package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString

롬복 사용시 주의사항
-시작글자가 외자인 필드명은 X
- productName -->올바른 표기
- pName -->잘못된 표기
=> jsp에서 EL표기법을 사용할 때 문제 발생함 ${pName} =/= getpName() 
*/


@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {   //클래스를 만들땐 추상화 먼저!
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private String age;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
	

}
