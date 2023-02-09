package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SampleController2 {

	@GetMapping("doC") //doC매핑주소 등록
	public String doC(@ModelAttribute("msg2") String msg) {
		/* @ModelAttribute("msg2")는 msg2 피라미터 이름에 인자값을 담아서 전달한다. 웹주소창에 doC?msg2=값 형태의 get방식으로
		 * 값을 전달한다.
		 */
		System.out.println("전달된 값 : "+msg);
		return "result";//result가 뷰페이지 파일명이 된다. 그러므로 뷰리졸브 경로(뷰페이지경로)는 /WEB-INF/views/result.jsp 가 된다.
	}
}
