package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/*") //컨트롤러 자체 URL 매핑주소 등록
public class SampleController {
	@GetMapping("/all")
	public void doAll() {//리턴타입이 없는 경우는 매핑주소가 뷰페이지 파일명이 된다.
		System.out.println("do all cal access everybody");
	}
	@GetMapping("/member")
	public void doMember() {
		System.out.println("logined member");
	}
	@GetMapping("/admin")
	public void doAdmin() {
		System.out.println("admin only");
	}
}
