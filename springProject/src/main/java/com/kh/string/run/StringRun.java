package com.kh.string.run;

import com.kh.spring.member.controller.StringController;

public class StringRun {
	public static void main(String[] args) {
	
	StringController sc = new StringController();
	sc.constructorString();
	
	sc.equals(null);
	}
}