package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	@GetMapping("/accessError") /*403접근금지 에러에 대한 메시지 처리 */
	public void accessDenied(Model model) {
		System.out.println("aeecss Denied");
		model.addAttribute("msg", "Access Denied"); //msg 키 이름에 값을저장
	}//리턴타입이 없는 void 형이면 매핑주소= 뷰페이지 파일명
	
	
	
	
	
	
	
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		//리턴타입이 없는 void형이면 매핑주소인 customLogin인 뷰페이지 파일명이 된다.
		
		System.out.println("error :" + error);
		System.out.println("logout : " + logout);
		
		if(error !=null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}
		if(logout != null) {
			model.addAttribute("logout", "LogOut !!"); //logout 키이름에 값 저장
		}
	}
}
