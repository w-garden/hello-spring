package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //@Controller 애노테이션을 설정하면 해당 클래스는 스프링에서 컨트롤러로 인식한다.
public class SampleController {

	@RequestMapping("doA") //get or post로 접근하는 url 매핑주소를 처리. doA매핑주소 등록
	public void doA7() {
		//리턴타입이 없는 void형이면 매핑주소인 doA가 뷰페이지 파일명이 된다.
		System.out.println("doA매핑주소가 실행됨.");
	}
	
	@GetMapping("doB") //GET으로 접근하는 매핑주소를 처리. doB매핑주소 등록
	public void doB() {//뷰페이지 파일명은 doB.jsp가 된다.
		System.out.println("doB매핑주소가 실행됨.");
	}
}
