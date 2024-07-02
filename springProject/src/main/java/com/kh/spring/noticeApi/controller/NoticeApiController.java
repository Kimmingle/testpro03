package com.kh.spring.noticeApi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.noticeApi.model.service.NoticeApiService;
import com.kh.spring.noticeApi.model.vo.Message;
import com.kh.spring.noticeApi.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeApiController {
	//조회, 작성, 수정, 삭제를 mapping 타입으로 구분할거임
	//조회 : get(전제조회와 상세조회 구분은 상세조회에 /{id}를 더해줘서 식별함 ) 
	//작성 : post
	//수정 : put
	//삭제 : delete
	
	private final NoticeApiService noticeApiService;
	
	@GetMapping
	public ResponseEntity<Message> findAll() {
		
		List<Notice> noticeList = noticeApiService.findAll();
		
		
		if(noticeList.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
													.body(Message.builder()
															.message("조회결과가 존재하지 않습니다")
															.build());
			
		}
		Message responseMsg = Message.builder()
									.data(noticeList)
									.message("성공")
									.build();
//		model.addAttribute("noti", noticeList);
//		return "";
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMsg);
		
		//log.info("조회된 공지사항 목록 : {}", noticeList);
		
		
	}
	
	
	@GetMapping("/{id}") //상세조회
	public ResponseEntity<Message> findById(@PathVariable int id){  //@PathVariable로 넘어온 id값 변수에 저장
		//ResponseEntity : 응답할 객체를 만들어서 보내줘라 
		Notice notice = noticeApiService.findById(id);
		
		//System.out.println(notice);
		//조회결과가 없으면 nul
		if(notice == null ) {
			return ResponseEntity.status(HttpStatus.OK)
									.body(Message.builder()
											.message("결과 없음 ")
											.build());
		}
		Message responseMsg = Message.builder()
										.message("성공")
										.data(notice)
										.build();
		//restController로 선언했기 때문에 데이터만 반환할 수 있음
		//근데 가공?시켜서 보내야함
		
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMsg);	//	responseMsg 매세지 객체를 응답
	}
	
	@PostMapping  //postMapping으로 받아서  notice로 가공할것 일부러 필드명이랑 동일만 이름으로 객체 만듦
	public ResponseEntity<Message> save(Notice notice) {
		int result = noticeApiService.save(notice);	
		
		
		if(result ==0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder()
																	.message("추가안댐")
																	.build());
			
		}
		
		Message responseMsg = Message.builder().data("공지사항 추가 성공")
									.message("서비스요청 성공")
									.build();
		return ResponseEntity.status(HttpStatus.OK).body(responseMsg);

							
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id){
		
//		int result = noticeApiService.delete(id);
//		
//		if(result==0) {
//			return ResponseEntity.status(HttpStatus.OK).body(Message.builder()
//																.message("게시글 존재하지 않음")
//																.build());
//		}
//		Message responseMsg = Message.builder().data("삭제성공").message("서비스처리성공").build();
		
		//return ResponseEntity.status(HttpStatus.OK).body(responseMsg);
	}
	
	@PutMapping
	public ResponseEntity<Message> update(@RequestBody Notice notice){
		
		int result = noticeApiService.update(notice);
		
		if(result==0) {
			return ResponseEntity.status(HttpStatus.OK).body(Message.builder()
															.message("공지 변경 실패")
															.build());
					
		}
		
		Message responseMsg = Message.builder()
										.data(result)
										.message("공지 변경 성공")
										.build();
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMsg);
	}
	

}
