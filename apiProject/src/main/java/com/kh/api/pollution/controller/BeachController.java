package com.kh.api.pollution.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("beach")
public class BeachController {
	
	
	

	@GetMapping(produces="applcation/json; charset=UTF-8")
	public String info(int pageNo) throws IOException {
		
		
		
		StringBuilder sb = new StringBuilder();
		//sb.append("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty");
		sb.append("http://apis.data.go.kr/6260000/BusanBeachInfoService/getBeachInfo");
		sb.append("?serviceKey=");
		sb.append(ApiController.SERVICE_KEY);
		sb.append("&pageNo=" +pageNo); 
		sb.append("&numOfRows=10"); 
		sb.append("&resultType=json");
		//sb.append(URLEncoder);
		//필수요소 두개
		
		
		String url = sb.toString();
		
		
		//자바코드로 url로 요청을 보낼것 -HttpURLConnection 객체가 필요함
		//먼저 url객체도 필요
		
		URL requestUrl = new URL(url);  //java.net.URL로 객체를 생성함 => 생성자 호출시 url을 인자값으로 전달
		
		
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();  //자식으로 형변환?
		
		urlConnection.setRequestMethod("GET");  //요청에 필요한 설정
		

		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  //보조스트림은 기반스트림없이 단독으로 사용할 수 없다. 
		
		
		String responseData = "";
		String line;
		
		while((line = br.readLine()) != null) {
			responseData += line;
		}
		
		//String responseData = br.readLine();
		
		br.close();
		urlConnection.disconnect();
		
		
		
		return responseData;
		
	}
}
