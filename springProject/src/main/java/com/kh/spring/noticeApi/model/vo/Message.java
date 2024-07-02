package com.kh.spring.noticeApi.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Message {
	
	
	private String message;  //요청 성공 유무
	private Object data;

}
