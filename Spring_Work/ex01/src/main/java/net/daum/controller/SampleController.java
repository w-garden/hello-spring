package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/*") //컨트롤러 자체 URL 매핑주소 등록
public class SampleController {

	@GetMapping("/all")  //all 매핑주소 등록, 로그인 하지 않은 사용자도 접근 가능하다. 
	public void doAll() {//리턴타입이 없는 경우는 매핑주소가 뷰페이지 파일명이 된다.
		System.out.println("do all can access EveryBody");		
	}//doAll() => 뷰페이지 경로가 /WEB-INF/views/sample/all.jsp
	
	@GetMapping("member") //로그인 한 사용자들만 접근할 수 있는 매핑주소
	public void doMember() {
		System.out.println("logined member");
	}//doMember()
	
	@GetMapping("/admin") //로그인 한 사용자 중에서  관리자 권한을 가진 사용자만 접근가능
	public void doAdmin() {
		System.out.println("admin only");
	}
}
