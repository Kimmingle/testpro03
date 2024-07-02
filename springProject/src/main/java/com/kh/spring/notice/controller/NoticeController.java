package com.kh.spring.notice.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.notice.model.service.NoticeService;
import com.kh.spring.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService noticeService;
	
	@GetMapping("notiList")
	public String forwarding(@RequestParam(value="page", defaultValue="1") int page, Model model) {
		
		//RowBounds 쓴거
				int listCount;   //현재 일반게시판의 글 개수 => BOARD테이블로부터 SELET COUNT(*)활용해서 조회할 것
				int currentPage;  // 현재 페이지의 사용자가 요청한 페이지 => 앞에서 넘길것
				int pageLimit;   //페이지 하단에 보여질 페이징바의 개수 => 10개로 고정
				int noticeLimit;   //한 페이지에 개시글을 몇개 보여줄건지 => 10개로 고정 (나중에 검색할땐 또 페이징 다시 해야함)
				
				int maxPage;  //가장 마지막 페이지가 몇번 페이지인지 (총 페이지의 개수)
				int startPage;  // 페이지 하단에 보여질 페이징바의 시작 수 
				int endPage;  // 페이지 하단에 보여질 페이징바의 끝 수 
				
				
				listCount = noticeService.noticeCount();  //listCount : 총 게시글의 수 
				
				
				currentPage = page;
				pageLimit = 10;
				noticeLimit = 10;
				
				//currentPage : 현재 페이지(사용자가 요청한 페이지)
				currentPage= page;
				//log.info("게시글의 총 개수 :{},  현재 요청페이지 :{}", listCount , currentPage);
				
				
				// maxPage : 가장 마지막 페이지ㅏ 몇번 메이지이지 (총 페이지 개수 )
				
				
				//공식 구하기
				//단 boardLimit100이라는 가정하에 규직을 찾아보자
				//나눗셈 결과네 소수점을 붙여서 올림처리를 할 경우 
				
				
				maxPage = (int)Math.ceil((double) listCount / noticeLimit);
				
				//Math math = new math();
				
				
				// - startPage : 1, 11, 21, 31, 41, => n* 10+1
				
				startPage = (currentPage-1)/pageLimit * pageLimit +1;
				//endPage = (currentPage-1)/pageLimit + pageLimit -1;
				endPage = startPage + pageLimit - 1;
				//start, Limit에 영향을 받음 (단, maxPag도 마지막 페이징바에 대해 영향을 끼침)
				
				//endPage= startPage+pageLimit -1;
				
				//start페이지가 1이라서 end가 10이 들어갔는데 
				//max가 다?
				if(endPage > maxPage) endPage = maxPage;
				
//				PageInfo pageInfo = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
//													maxPage, startPage, endPage);
				
				PageInfo pageInfo = PageInfo.builder() 
											.listCount(listCount)
											.currentPage(currentPage)
											.pageLimit(pageLimit)
											.boardLimit(noticeLimit)
											.maxPage(maxPage)
											.startPage(startPage)
											.endPage(endPage)
											.build();
				
				//boardLimit이 10이라는 가정하에 currentPage == 1/2/3  => 시작값은 1, 끝값 10 /11, 20 / 시작값 21, 끝값30
				//시작값 = (currentPage -1) * boardLimit +1 ;
				//끝값 = 시작값 + boardLimit -1;
				
				Map<String, Integer> map = new HashMap();
				
				int startValue = (currentPage-1) * noticeLimit +1;
				int endValue = startValue + noticeLimit -1;
				
				map.put("startValue", startValue);
				map.put("endValue", endValue);
				
				List<Notice> noticeList = noticeService.findAll(map);
				
				log.info("조회된 글의 개수 : {}", noticeList.size());
				log.info("------");
				log.info("조회된 게시글 목록 : {}", noticeList);
				
				model.addAttribute("list",noticeList);
				model.addAttribute("pageInfo",pageInfo);
		
		return "notice/notiList";
	}
	
	@GetMapping("noticeForm.do")
	public String noticeForm() {
		return "notice/notiForm";
	}
	

	@PostMapping("insertNoti.do")
	public String insertNoti(Notice notice, HttpSession session, Model model, MultipartFile upfile) {  //파일이 있는지 없는지 확인해야함  MultipartFile[] 여러개의 파일이 배열로 한번에 들어옴
		log.info("게시글 정보 : {}", notice);
		//log.info("게시글 정보 : {}", upfile);
		
		//upfile로 파일이 있는지 없는지 먼저 확인 
		
			
			
		if(noticeService.insert(notice)>0) {
			
			session.setAttribute("alertMsg", "게시글 작성 성공");
			
			return "redirect:notiList";
		}else {
			model.addAttribute("erroeMsg", "실패");
			return "common/errorPage";
		}
		//return "redirect:/boardForm.do";
		
	
	}

}