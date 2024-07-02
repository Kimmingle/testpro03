package com.kh.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kh.ajax.model.vo.Menu;

@Controller
public class AjaxController {
	
	//HttpServletResponse 객체로 응답데이터응 응랍하기
	/*
	
	@GetMapping("ajax1.do")
	public void calSum(String menu, int amount, HttpServletResponse response) throws IOException {
		
		System.out.println("사용자가 입력한 메뉴 :" +menu);
		System.out.println("사용자가 입력한 수량 :" +amount);
		
		
		int price = 0;
		
		
		switch(menu) {
			
		case "알밥" : price=1000; break;
		case "돈까스" : price=2000; break;
		case "서브웨이" : price=3000; break;
		case "김치찜" : price=4000; break;
		case "막국수" : price=5000; 
		
		
		}
		
		price *= amount;
		//System.out.println(price);
		
		//서비스 다녀와서 요청처리 끝남
		//price 반환할 데이터임
		
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(price);
	}
	
	
	*
	*/
	
	
	@ResponseBody
	@GetMapping(value="ajax1.do", produces="text/html; charset=UTF-8")
	public String calSum(String menu, int amount) {

		int price = 0;
		
		
		switch(menu) {
			
		case "알밥" : price=1000; break;
		case "돈까스" : price=2000; break;
		case "서브웨이" : price=3000; break;
		case "김치찜" : price=4000; break;
		case "막국수" : price=5000; 
		
		
		}
		
		price *= amount;
		
		//price는 int지만 return을 String으로 해야함
		return String.valueOf(price);
		
	}
	
	
	/*ResponseBody적으면 어케됨? */
	@ResponseBody
	@GetMapping(value="ajax2.do", produces="application/json; charset=UTF-8")
	public String selectMenu(int menuNumber) {
		
		//요청 처리를 잘했다는 가정하에 데이터 응답
		//자바에서는 배열로 보내거나 객체의 필드값에담아 보내거나
		//자바스크립트로 보낼때는 자바스크립트가 해석할 수 있는 형태로 만들어서 보내줘야함
		//
		
		Menu menu = new Menu(1, "순두부", 9800, "순두부");
		/*
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append("menuNumber: "+ "'" +menu.getMenuNumber()+"',");
		sb.append("menuName:   "+"'"  +menu.getMenuName()+"',");
		sb.append("menuPrice:  "+ "'" +menu.getPrice()+"',");
		sb.append("menuMaterial: "+"'" +menu.getMaterial()+"'");
		sb.append("}");
		
		return sb.toString();
		*/
		
		JSONObject jobj = new JSONObject();
		jobj.put("menuNumber", menu.getMenuNumber());
		jobj.put("menuName", menu.getMenuName());
		jobj.put("menuPrice", menu.getPrice());
		jobj.put("menuMaterial", menu.getMaterial());
		
		return jobj.toJSONString();
		
	}
	
	@ResponseBody
	@GetMapping(value="ajax3.do", produces="application/json; charset=UTF-8")
	public String ajax3Method(int menuNumber) {
		Menu menu = new Menu(1, "순두부", 9800, "순두부");
		
		
		return new Gson().toJson(menu);
	}
		
	
	@ResponseBody
	@GetMapping(value="find.do", produces="application/json; charset=UTF-8")
	public String findAll() {
		
		//Menu menu = new Menu(1, "순두부", 9800, "순두부");
		
		List<Menu> menus = new ArrayList();
		menus.add(new Menu(1, "순두부찌개", 9500, "순두부"));
		menus.add(new Menu(2, "김치찌개", 9500, "김치"));
		menus.add(new Menu(3, "된장찌개", 9500, "된장"));
		//총 새개의 자바스크립트 객체가 만들어져야함
		//객체 배열로 만들 수 있음 [{}, {}, {}]
		//[{}, {}, {}]을 json으로 응답해야함 
		
		/*
		
		JSONObject jobj1 = new JSONObject();
		jobj1.put("menuNumber", menus.get(0).getMenuNumber());
		jobj1.put("menuName", menus.get(0).getMenuName());
		jobj1.put("menuPrice", menus.get(0).getPrice());
		jobj1.put("menuMaterial", menus.get(0).getMaterial());
		//이런식
		
		JSONArray jArr = new JSONArray();
		
		//jArr.add(jobj1);
		
		
		
		for(int i=0; i< menus.size(); i++) {}  //이런식으로 for문써서 add해줘야하는데 개귀차늠
		// ==> gson이 해줌
		*/
		
		
		return new Gson().toJson(menus);
	}
		
	
	
	
	
	
}
