package com.kh.spring.member.controller;

public class StringController {
	//String class = 불변
	// 왜 불변이냐 문자열 같은 경우 다른 타입들과 다르게 어느정도의 크기를 쓸지 모르니까 크기 지정하기가 애매함 
	//--> 가변형 배열으로 만들자!
	//배열의 특징 : 논리적인 구조와 물리적인 구조가 동일하다 즉 내가 넣은 값이 논리적인 순서와 동일하게 값이 들어간다
	
	
	//객체 생성 2가지 방법
	//1. 개입연산자를 통해서 String 리터럴을 대입
	//2. 생성자를 호츨해서 String 객체로 만들어주는 방식
	
	//2번 방법
	public void constructorString() {
		String str1 = new String("hello");
		String str2 = new String("hello");
		
		String[] strArr = {};
		
		System.out.println(str1);
		System.out.println(str2);
		
		System.out.println(str2);
		
		//1.Stringzmffotmdml totring의 결루 실제 담겨있는 문자열 리터럴을 반환하게끔 오버라이딩
		System.out.println(str1.equals(str2)); //주소값은 다르지만 같다고 나온다.
		
		
		//HashCode
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		System.out.println("Hello".hashCode());
		
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		System.out.println(str1==str2);
	}
	
	public void assignToString() {
		
		String str1 ="hello";
		String str2 = "hello";
		
		System.out.println(str1);
		System.out.println(str2);
		
		System.out.println(str1.equals(str2));
		
		System.out.println(str1.hashCode());
		System.out.println(str1.hashCode());
		
	}
	
	public void stringPool() {
		
		String str = "hello";
		//이 string이 pool에 담김
		
		String newStr = "hello";
		
		
		//객체는 항상 새로운 주소를 참조한다.
		
		StringBuffer st = new StringBuffer();
		
	}
	
	

}
