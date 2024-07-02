package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.template.PageTemplate;
import com.kh.spring.common.model.vo.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("reply")
public class BoardController{
	
	
	private final BoardService boardService;
	
	@GetMapping("boardlist")
	public String forwarding(@RequestParam(value="page", defaultValue="1") int page, Model model) {
		
		//페이징 처리
		
		//RowBounds 안쓴거
		
		//RowBounds 쓴거
		int listCount;   //현재 일반게시판의 글 개수 => BOARD테이블로부터 SELET COUNT(*)활용해서 조회할 것
		int currentPage;  // 현재 페이지의 사용자가 요청한 페이지 => 앞에서 넘길것
		int pageLimit;   //페이지 하단에 보여질 페이징바의 개수 => 10개로 고정
		int boardLimit;   //한 페이지에 개시글을 몇개 보여줄건지 => 10개로 고정 (나중에 검색할땐 또 페이징 다시 해야함)
		
		int maxPage;  //가장 마지막 페이지가 몇번 페이지인지 (총 페이지의 개수)
		int startPage;  // 페이지 하단에 보여질 페이징바의 시작 수 
		int endPage;  // 페이지 하단에 보여질 페이징바의 끝 수 
		
		
		listCount = boardService.boardCount();  //listCount : 총 게시글의 수 
		
		
		currentPage = page;
		pageLimit = 10;
		boardLimit = 10;
		
		//currentPage : 현재 페이지(사용자가 요청한 페이지)
		currentPage= page;
		//log.info("게시글의 총 개수 :{},  현재 요청페이지 :{}", listCount , currentPage);
		
		
		// maxPage : 가장 마지막 페이지ㅏ 몇번 메이지이지 (총 페이지 개수 )
		
		
		//공식 구하기
		//단 boardLimit100이라는 가정하에 규직을 찾아보자
		//나눗셈 결과네 소수점을 붙여서 올림처리를 할 경우 
		
		
		maxPage = (int)Math.ceil((double) listCount / boardLimit);
		
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
		
//		PageInfo pageInfo = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
//											maxPage, startPage, endPage);
		
		PageInfo pageInfo = PageInfo.builder() 
									.listCount(listCount)
									.currentPage(currentPage)
									.pageLimit(pageLimit)
									.boardLimit(boardLimit)
									.maxPage(maxPage)
									.startPage(startPage)
									.endPage(endPage)
									.build();
		
		//boardLimit이 10이라는 가정하에 currentPage == 1/2/3  => 시작값은 1, 끝값 10 /11, 20 / 시작값 21, 끝값30
		//시작값 = (currentPage -1) * boardLimit +1 ;
		//끝값 = 시작값 + boardLimit -1;
		
		Map<String, Integer> map = new HashMap();
		
		int startValue = (currentPage-1) * boardLimit +1;
		int endValue = startValue + boardLimit -1;
		
		map.put("startValue", startValue);
		map.put("endValue", endValue);
		
		List<Board> boardList = boardService.findAll(map);
		
		log.info("조회된 글의 개수 : {}", boardList.size());
		log.info("------");
		log.info("조회된 게시글 목록 : {}", boardList);
		
		model.addAttribute("list",boardList);
		model.addAttribute("pageInfo",pageInfo);
		
		return "board/list";
	}
	
	
	@GetMapping("search.do")
	public String search(String condition, String keyword, Model model,  @RequestParam(value="page", defaultValue="1") int page) {
		
		log.info("검색 조건 : {}",condition);
		log.info("검색 키워드 : {}", keyword);
		
		//사용자가 선택한 조건과 입력한 키워드를 가지고
		//페이징처리를 끝낸 후 검색결과를 들고가야함 
		
		//"writer", "title", "content"
		//사용자가 입력한 키워드
		
		// 어디로 가져가냐 1. String[], List, Set, Map, Class
		// 이중에 제일 탁월한 선택은?
		Map<String, String> map = new HashMap();
		map.put(condition, condition);
		map.put(keyword, keyword);
		
		int searchCount = boardService.searchCount(map);
		int currentPage = page;
		int pageLimit = 3;
		int boardLimit = 3;
		
		//int maxPage = (int)Math.ceil((double)searchCount/ boardLimit);
		
		PageInfo pageInfo = PageTemplate.getPageInfo(searchCount,
													currentPage,
													pageLimit,
													boardLimit);
		
		RowBounds rowBounds = new RowBounds((currentPage-1) * boardLimit, boardLimit);
		//마이바티스에서는 페이징처리를 이해 rowbound라는 클래스를 제공한다. 
		
		List<Board> boardLisr = boardService.findByConditionAndKetword(map, rowBounds);
		
		model.addAttribute("list",boardLisr);
		
		log.info("검색 조건에 부합하는 행의 수  : {}", searchCount);
		
		return "board/list";
	}
	
	
	@GetMapping("boardForm.do")
	public String boardForm() {
		return "board/boardForm";
	}
	
	
	@PostMapping("insert.do")
	public String insert(Board board, HttpSession session, Model model, MultipartFile upfile) {  //파일이 있는지 없는지 확인해야함  MultipartFile[] 여러개의 파일이 배열로 한번에 들어옴
		log.info("게시글 정보 : {}", board);
		//log.info("게시글 정보 : {}", upfile);
		
		//upfile로 파일이 있는지 없는지 먼저 확인 
		if(!upfile.getOriginalFilename().equals("") ) {   //빈 문자열(파일이 없음)
			//파일을 먼저 올려야 파일의 경로를 DB에 저장함
			//주의 ) 파일의 이름이 겹칠경우 덮어씌워짐
			
			//kh_년월일시분초_랜덤한 값.확장자  로 파일명 만들어줄거임
			
			 //확장자 구하기
//			String originName = upfile.getOriginalFilename();
//			
//			String ext = originName.substring(originName.lastIndexOf("."));   //originName는 배열이라 인덱스 쓸 수 있음
//		
//			int num = (int)(Math.random() * 900 ) +100;  //랜덤
//			
//			String current = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
//			String changeName = "KH_" + current +"_" +num+ ext;
//			
//			try {
//				upfile.transferTo(new File(savePath + changeName));
//			} catch (IllegalStateException e) {
//				
//				e.printStackTrace();
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
			
			//saveFile(upfile, session);
			
			board.setOriginName(upfile.getOriginalFilename());
		
			board.setChangeName("/resources/uploadFiles/" + saveFile(upfile, session));
		}
		
		if(boardService.insert(board)>0) {
			
			session.setAttribute("alertMsg", "게시글 작성 성공");
			
			return "redirect:boardlist";
		}else {
			model.addAttribute("erroeMsg", "실패");
			return "common/errorPage";
		}
		//return "redirect:/boardForm.do";
		
	}
	
	//localhost/spring/board-detail?boardNo=???  이렇게 들어왔을 것
	@GetMapping("board-detail")
	public ModelAndView findById(int boardNo, ModelAndView mv) {
	//	public String forwarding(@RequestParam(value="page", defaultValue="1") int page, Model model) {
	// requestParam는 String으로 반환해주는데 메소드는 int를 인자로 받음 =>형변환이 아닌 parsing해야함  ex. int abc = Integer.parseInt("123");
		
		//데이터 가공, 서비스 호출, 응답화면 지정 (여기선 인자가 하나라 가공할거 없으니까 바로 서비스 호출)
		
		//boardService.increaseCount(boardNo); //서비스 갔다오면 0아니면 1이 담김 -성공했으면 상세조회, 실패했으면 
		
		 
		
		if(boardService.increaseCount(boardNo) > 0){
			mv.addObject(boardService.findById(boardNo))
			.setViewName("/board/boardDetail");
			
		}else {
			mv.addObject("erroeMsg", "게시글 상세조회에 실패").setViewName("common/errorPage");
		}
		
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("/board/boardDetail");
//		
		return mv;
	}
	
	/*
	 * deleteById : Client(게시글 작성자)에게 정수형의 boardNo(BOARD테이블의 PK)를 전달받아서 BOARD테이블의 존재하는 STATUS컬럼의 값을 'N'으로
	 * @param boardNo : 각 행을 식별하기위한 pk
	 * @param filePath : 요청처리 성공 시 첨부파일을 제거하기 위해 파일이 저장되어있는 경로 및 파일명
	 * 
	 * @return : 반환된 View의 논리적인 경로
	 * 
	 * */
	@PostMapping("boardDelete.do")
	public String deleteById(int boardNo, String filePath, Model model, HttpSession session) {
		
		if(boardService.delete(boardNo)>0) {
			
			if(!"".equals(filePath)) {
				//이렇게 빈 문자열을 기준으로 비교를 하면 null exception이 발생하지 않음(null에 equal를 할 순 없지만 equal를 하기 위해서 null을 호출할수는 있으니까)
				new File(session.getServletContext().getRealPath(filePath)).delete();
			}
			
			session.setAttribute("alertMsg","게시글 삭제 성공");
			return "redirect:boardlist";
		} else {
			model.addAttribute("erroeMsg", "게시글실패");
			return "common/errorPage";
		}
		
		
	}
	
	@PostMapping("boardUpdateForm.do")
	public ModelAndView updateForm(ModelAndView mv, int boardNo) {
		
		mv.addObject("board", boardService.findById(boardNo))
		.setViewName("board/boardUpdate");
		return mv;
	}
	
	
	
	@PostMapping("board-update.do")
	public String update(Board board, MultipartFile reUpFile,HttpSession session) {
		
		//DB가서 BOARD테이블 update
		
		//새로운 첨부파일이 존재하는지 확인해야함 
		if(!reUpFile.getOriginalFilename().equals("")) {
			
			
			
			board.setOriginName(reUpFile.getOriginalFilename());
			board.setChangeName(saveFile(reUpFile, session));
		}
		
		if (boardService.update(board) > 0) {
			session.setAttribute("alerMsg", "잘바꿈");
			return "redirect:board-detail?boardNo="+board.getBoardNo();
			
		} else {
			session.setAttribute("errorMsg", "실패");
			return "common/errorPage";
		}
	}
	
	
	//이런 메소드도 컨트롤러에 적을 수 있음
	public String saveFile(MultipartFile upfile, HttpSession session) {
		String originName = upfile.getOriginalFilename();
		
		String ext = originName.substring(originName.lastIndexOf("."));   //originName는 배열이라 인덱스 쓸 수 있음
	
		int num = (int)(Math.random() * 900 ) +100;  //랜덤
		
		String current = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
		String changeName = "KH_" + current +"_" +num+ ext;
		
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/resources/uploadFiles/" + changeName;
	}
	
	
	@GetMapping("img-board")
	public String imges(Model model) {
		
		//List<Board> img = boardService.selectImg();  이거 왜 model로 바꿈?
		
		model.addAttribute( "board", boardService.selectImg());
		return "board/imgList";
	}
	
	
	@ResponseBody      //나중에 json으로 받아올거라서?
	@GetMapping(value="reply", produces="application/json; charset=UTF-8")
	public String selectReply(int boardNo) {
		
		
		
		return new Gson().toJson(boardService.selectReply(boardNo));
	}
	
	
	@ResponseBody
	@PostMapping("reply")
	public String saveReply(Reply reply){
		
		
		return boardService.insertReply(reply)>0? "success":"fail";
	}
	
	@ResponseBody
	@GetMapping("board-reply")
	public Board boardAndReply(int boardNo) {
		
		return boardService.boardAndReply(boardNo);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
