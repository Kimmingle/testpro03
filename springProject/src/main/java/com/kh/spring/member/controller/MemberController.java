package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//컨트롤러가 해야할 일
//1. 데이터 가공
//2. 응답하여 지정

//@controller: 범용적인 컨트롤러

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;
	
//	@RequestMapping("/login.do")  //request mapping 타입의 handlermapping 등록
//	public void login() {
//		log.info("로그인 요청 보냄?");
//	}
//	





/*
 * spring에서 Handler가 전달값을 받는 방법
 *  1.HttpServletRequest를 이용해서 전달받기 (기존의 JSP/Servlet방식)
 *  
 *  @RequestMapping("/login.do") 
	public String login(HttpServletRequest request){
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		
		log.info("회원이 입력한 아이디 값 : {}", userId);
		log.info("회원이 입력한 비밀번호 값 : {}", userPwd);
  		
  
  		return "main";
	}
 * 
 *  2.RequestParam 애노테이션을 이용하는 방법
 *   -- request.getParameter("키값")로 value를 뽑아오는 역할을 대신 해주는 애노테이션
 *   
 *  3. RequestParam애노테이션을 생략하는 방법
 *  -- 단, 매개변수 식별자를 jsp의 name속성값(요청시 전달하는 값의 키값)과 동일하게 작성해주어야만 자동으로 같이 주입됨
 *  	단점이라고한다면 2의 defaultValue속성을 사용할 수 없음
 *  
 *  
 *  @RequestMapping("/login.do") 
	public String login(String id, String pwd){
		
		log.info("회원이 입력한 아이디 값 : {}", id);
		log.info("회원이 입력한 비밀번호 값 : {}", pwd);
  		return "main";
	}
 *  
 */
//	@RequestMapping("/login.do") 
//	public String login(String id, String pwd){
//		
//		log.info("회원이 입력한 아이디 값 : {}", id);
//		log.info("회원이 입력한 비밀번호 값 : {}", pwd);
//		//1. 데이터 가공 -> DTO에 데이터를 담아서 보냄 member.java
//		Member member = new Member(); //Member타입으로 변수를 생성하여 기본 생성자로  
//		member.setUserId(id);
//		member.setUserPwd(pwd);
//		
//		//1.5 서비스 호출
//		memberService.login(id, pwd);
//		
//		//응답화면 지정
//  		return "main";
//	}
//	
	
	/*
	4. 커멘드 객체
	- 요청시 전달값을 담고자하는 클래스의 타입을 지정한 뒤
	- 요청 시 전달값의 키값(jsp의 name속성값) 을 클래스의 담고자하는 필드명과 동일하게 작성
	- 스프링 컨테이너가 해당 객체를 기본 생성자로 생성한 후 내부적으로 setter메소드를 찾아서 요청 시 
	*/
	
//	@RequestMapping("login.do")
//	public String login(Member member) {
//		log.info("가공된 멤버 객체 : {}", member);
//		
//		Member loginMember = memberService.login(member);  //member객체의 메모리 주소를 넘김
//		
//		return "main";
//	}
//	
	/*
	REST방식의 URL만들기
	-  localhost/spring/member/12
	
	
	@GetMapping("member/{id}")
	public void restTest(@PathVariable String id) {
		log.info("앞단에서 넘긴 값 : {}", id);
		
	}
	*/
	/*
	 * 요청 처리 후 응답 데이터를 담고 응답페이지로 포워딩 또는 리다이렉트 하는법
	 * 1. 스프링에서 제공하는 Model객체를 사용하는 방법
	 * - 포워딩할 응답뷰로 전달하고자하는 데이터를 키-벨류 형태로 담을 수 있는 영역
	 * - Model객체는 requestScope
	 * 
	@PostMapping("login.do")
	public String login(Member member, Model model, HttpSession session) {
		
		Member loginUser = memberService.login(member);
		if(loginUser == null) {  // 로그인 실패 =>에러문구 requestScope에 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg","로그인 실패");
			
			return "common/errorPage";
			
		} else {
			session.setAttribute("loginUser", loginUser);
			return "redirect:/";
		}
		
		//실제로는 WEB-INF/views/main.jsp --접두사, 접미사 붙여짐
		//return "main";
		
	}
	*/


	@PostMapping("login.do")
	public ModelAndView login(Member member, ModelAndView mv, HttpSession session) {
		
		Member loginUser = memberService.login(member);
		//loginUser: 사용자가 입력한 아이디값 들어있을것
		//member 가 아니라 id만 있어도 됐었구나
		// 사용자가 입력한 userid와 status값이 Y와 일치하다면
		//login
		
		
		//null 예외처리
		//bcryptPasswordEncoder은 빈으로 등록했기 때문에 null일수가 없음
		//if(   bcryptPasswordEncoder.matches(member.getUserPwd(), loginUser.getUserPwd())&&loginUser != null ) {
		//이렇게하면 없는 아이디를 입력했을때 에러페이지가 아닌 500에러가 뜨게 된다. 
		//loginUser의 값이 null이지만 점연산자를 사용해 null.getUserPwd 가 되기 때문이다. 
		//해결 방법 : null검사를 하고 뒤에는 실행되지 않게 한다
		
		if( loginUser != null &&  bcryptPasswordEncoder.matches(member.getUserPwd(), loginUser.getUserPwd()) ) {
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("errorMsg", "로그인 실패")
			.setViewName("common/errorPage");
		}
		return mv;
		
	}
	
	@GetMapping("logout.do")  // a태그는 get
	public String logout(HttpSession session) {
		
		//sessionScope에 존재하는 loginUser제거
		//session이 없으니까 매개변수로 받을것
		//session.invalidate(); //세션 초기화(세션 날림- 다른 정보가 들어있다면 같이 날라감)
		session.removeAttribute("loginUser");  //이렇게하면 로그인 정보만 날라감!
		
		
		return "redirect:/";
	}
	
	@GetMapping("enroll.do")
	public String enrollForm() {
		
		//서블릿에서 뷰 리졸버
		//접두사와 접미사가  WEB-INF/views/[  ].jsp이니까 이 안에 들어갈 경로 return해줘야함 
		return "member/enrollForm";
	}
	
	@PostMapping("join.do")
	public String join(Member member, Model model) {
		
		log.info("회원가입 객체  : {}",member);
		
		log.info("평문 : {}", member.getUserPwd());
		
		
		String encPwd = bcryptPasswordEncoder.encode(member.getUserPwd());
		log.info("암호문 : {}", encPwd);
		
		member.setUserPwd(encPwd);
		// userPwd 필드에 평문이 아닌 암호문을 담아서 DB로 보내기
		String viewName = "";
		
		if(memberService.insert(member) > 0){
			viewName= "redirect:/";
			
		} else {
			model.addAttribute("errorMsg", "회원가입실패");
			viewName = "common/errorPage";
		}
		
		//1.한글이 깨짐  -> web.xml에 스프링이 제공하는 인코딩 필터 등록
		//2. 비밀번호가 사용자가 입력한 있는 그대로의 평문
		
		return viewName;
		
	}
	
	@GetMapping("mypage.do")
	public String mypage() {
		
		return "member/mypage";
	}
	
	@PostMapping("update.do")
	public String update(Member member,  HttpSession session, Model model) {
		
		log.info("수정요청 멤버 : {}", member);
		
		
		//0/1 들어옴
		if (memberService.update(member) > 0) {//성공했으면
			//포워딩하거나 redirect 할 수 있는데 redirect 써야함 
			//return "member/myPage";   mypage의 return이랑 코드 중복 <--유지보수가 어려움
			
			//sessionScope의 loginUser라는 키값으로 덮어씌워줄것 (안그럼 db의 데이터만 바뀌는거임 지금 사용자 정보는 로그인시점의 세션에 있음)
			//db의 업데이트된 값을 가져오려면 select하는수밖에 없음
			//memberService.login(member);
			
			session.setAttribute("loginUser", memberService.login(member));
			
			//성공했다면 창 띄워줄건데 성공메세지를 담아주자
				//메세지 모델에 담으면 메세지 안뜸 왜? return을 mypage.do로 보내고 있기 때문에
				//model.addAttribute("alertMsg", "회원 정보 수정 성공");
			session.setAttribute("alertMsg", "수정 성공 ");  //세션은 모든 jsp에서 사용 가능하니까 
			
			return "redirect:mypage.do";  //--> 유지보수 굿
			
			
		}else {
			return "common/errorPage";
		}
		
		
	}
	
	@PostMapping("delete.do")
	public String delete(Member member, HttpSession session, Model model) {
		//Object obj = (Object)new Member; 타입 맞춰야함
		
		
		//매개변수 Member => userPwd : 사용자가 입력한 비밀번호 평문
		//session의 loginUser키값으로 저장되어있는 Member객체의 userPwd필드 :DB에 기록된 암호화된 비밀번호 
		 
		String plainPwd = member.getUserPwd();
		String encPwd = ((Member)session.getAttribute("loginUser")).getUserPwd();  //멤버의 주소
		
		
		//member.getUserId() 하니까 유저 아이디가 아니라 null담김.. 그래서session에 저장되어있는 id가져옴
		String getID = ((Member)session.getAttribute("loginUser")).getUserId();
	
		System.out.println(member.getUserId());  //null
		System.out.println(member.getUserPwd());  //1234
		System.out.println(member.getAge());   //null

		System.out.println(getID);   //id01
		
		
		if(bcryptPasswordEncoder.matches(plainPwd, encPwd)) {  //먼저 비번 맞게 썼는지 확인
			
			//if(memberService.delete(member.getUserId()) > 0) {  
			
			if(memberService.delete(getID) > 0) {   //getID변수에 담은 유저 아이디 탈퇴시킴
				
				session.setAttribute("alertMsg", "탈퇴성공");
				session.removeAttribute("loginUser");
				
				return "redirect:/";
			}else {
				
				System.out.println(((Member)member).getUserId());
				
				model.addAttribute("errorMsg", "회원탈퇴실패");
				return "common/errorPage";
			}
			
		}else {
			session.setAttribute("alertMsg", "비밀번호 불일치");
			
			return "redirect:mypage.do";
		}
		
		
		
	}
	
	
	@ResponseBody
	@GetMapping("idCheck.do")
	public String checkId(String checkId) {
//		log.info(checkId);
//		
//		int result =  memberService.idCheck(checkId);
//		//NNNNN돌려준다고 가정
//		
//		if(result >0) {
//			
//			return "NNNNN";
//		
//		}else {
//			return "NNNNY";
//			
//		}
//		
//		return result > 0 ? "NNNNN" : "NNNNY";
		
		return memberService.idCheck(checkId) > 0? "NNNN" : "NNNY";
	}
	
	
	
	

}
