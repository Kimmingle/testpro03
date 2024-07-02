package com.kh.spring.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


//조회수 많은거 json형태로 받을것
//이 컨트롤러는 /boards로 시작하는요청이 들어오면 처리를 해줄 컨트롤러
//@RestController 애노테이션 쓰면 클래스 안에 있는 메서드는 자동으로 ResponseBody가 됨
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="boards", produces="application/json; charset=UTF-8")  //main에서 url : 'borads' 보내준것 =
public class BoardsController {
	
	private final BoardService boardService;
	
	@GetMapping
	public List<Board> findTopFiveBoard() {
		
		return boardService.findTopBoard();
	}
	
	@GetMapping("/{boardNo}")
	public Board findByBoardNo(@PathVariable int boardNo) {  //@PathVariable: boardNo를 빼내는 용도
		log.info("넘어온 pk : {}", boardNo);
		
		return boardService.findById(boardNo);
	}
	

}
