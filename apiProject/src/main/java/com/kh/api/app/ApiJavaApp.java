package com.kh.api.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.api.model.vo.AirVO;

public class ApiJavaApp {
	
	public static final String SERVICE_KEY ="1Zs47eQVwXWxHYk9nlaYchfwjMaeSRSu9ZQU5ErLap4xzHJ%2FZb%2BVh9H0IP6m1EQlsw9MmVMs0ArLP7OGbz4t3Q%3D%3D";

	public static void main(String[] args) throws IOException {
		
		//System.out.println("하잉");
		
		//순수 자바만으로 client program을 만들어서 시도병 api서버로 요청보내고 응답받기
		
		//먼저, 요청보낼 url이 필요함 (String타입으로 만들것)
		
		StringBuilder sb = new StringBuilder();
		
		//sb.append("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty");
		sb.append("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty");
		sb.append("?serviceKey=");
		sb.append(SERVICE_KEY);
		sb.append("&sidoName="); 
		sb.append(URLEncoder.encode("서울", "UTF-8")); 
		sb.append("&returnType=json");
		//sb.append(URLEncoder);
		//필수요소 두개
		
		
		String url = sb.toString();
		
		System.out.println(url);
		
		//자바코드로 url로 요청을 보낼것 -HttpURLConnection 객체가 필요함
		//먼저 url객체도 필요
		
		URL requestUrl = new URL(url);  //java.net.URL로 객체를 생성함 => 생성자 호출시 url을 인자값으로 전달
		
		//생성한 url객체를 가지고 HttpURLConnection 객체를 생성
		
		//HttpURLConnection urlConnection = requestUrl.openConnection();    //openConnection의 반환타입은 url이지만 나는 HttpURLConnection으로 받아서 에러남
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();  //자식으로 형변환?
		
		urlConnection.setRequestMethod("GET");  //요청에 필요한 설정
		
		//서버가 보내주는 데이터를 입력받고싶음 -자바는 Input Stream으로만 데이터 입력받을 수 있음
		urlConnection.getInputStream();  //바이트스트림이기 때문에 문자열 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  //보조스트림은 기반스트림없이 단독으로 사용할 수 없다. 
		
		/*
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());  //더이상 읽을게 없으면 null
		*/
		
		
		/*
		while(true) {
			String value = br.readLine();
			
			if(value !=null) {
				System.out.println(value);
			}else {
				break;
			}
			
		}
		*/
		
		String responseXml ="";
		while((responseXml = br.readLine()) != null) {
			System.out.println(responseXml);
		}
		
		
		String responseJson = br.readLine();
		
		
//		AirVO air = new AirVO();
//		air.setKhaiValue("");
		//문자열데이터를 json
		
		//라이브러리
		//JsonObject, JsonArray : json을 자바데이터로 바꾸는것 
		//+JsonParser 
		
		//
		//JsonObject, JsonArray : 자바 데이터를 json으로 바꾸는것  (JSON라이브러리)
		
		JsonObject jsonObj = JsonParser.parseString(responseJson).getAsJsonObject();//우리가 파싱하고싶은 문자열 데이너 넣어줌
		
		//System.out.println(responseJson); 
		//System.out.println("---");
		//System.out.println(jsonObj);   //두개 결과 같음-오ㅐ?
		
		
		JsonObject responseObj = jsonObj.getAsJsonObject("response"); 
		//System.out.println("---");
		//System.out.println(responseObj);
		
		JsonObject bodyObj = responseObj.getAsJsonObject("body");
		
		//tptal40을 뽑아쓰고싶은데 뭘로 써야할까 byte, short, int, long
		int totalCount = bodyObj.get("totalCount").getAsInt();
		
		JsonArray items = bodyObj.getAsJsonArray("items");
		//System.out.println(items);
		
		
		
		
		//air객체의 필드에 값을 하나하나 담음
		
		List<AirVO> list = new ArrayList();
		
		for(int i =0; i<items.size(); i++) {
			
			JsonObject item = items.get(i).getAsJsonObject();
			
			AirVO air = new AirVO();
			
			air.setPm10Value(item.get("pmValue").getAsString());
			air.setPm10Value(item.get("pmValue").getAsString());
			air.setPm10Value(item.get("pmValue").getAsString());
			air.setPm10Value(item.get("pmValue").getAsString());
			air.setPm10Value(item.get("pmValue").getAsString());
			air.setPm10Value(item.get("pmValue").getAsString());
			
			list.add(air);
		}
		
		for(AirVO air : list) {
			System.out.println(air);
			
		}
			
		br.close();
		urlConnection.disconnect();
		
		
		}
	
	

}
