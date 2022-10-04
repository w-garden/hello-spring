package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {

	@GetMapping("/accessError") /* 403 접근금지 에러가 발생했을때 실행되는 부분 */
	public void accessDenied(Model model) {
		System.out.println("access Denied");
		model.addAttribute("msg","Access Denied");//msg키이름에 값을 저장
	}//리턴타입이 없는 void형이면 매핑주소가 뷰페이지 파일명이 된다.뷰페이지 파일명이 accessError.jsp가 된다.
	
	
	@GetMapping("/customLogin")
	public void loginInput(String error,String logout,Model model) {
		//리턴 타입이 없는 void형이면 매핑주소인 customLogin인 뷰페이지 파일명이 된다.
		
		System.out.println("error : "+error);
		System.out.println("logout : "+logout);
		
		if(error != null) {
			model.addAttribute("error","Login Error Check Your Account");
		}
		if(logout != null) {
            model.addAttribute("logout", "LogOut !!");//logout키이름에 값 저장			
		}
	}//customLogin 매핑주소
	
	
	@GetMapping("/customLogout") //로그아웃시 get으로 접근하는 매핑주소를 처리
	public void logoutGet() {
		//리턴 타입이 없는 void형이면 매핑주소가 뷰페이지 파일명이 된다.=>customLogout.jsp
	}
	
	@PostMapping("/customLogout") //매핑주소가 같아도 method방식을 다르게 하면 사용가능하다.post로 접근하는 매핑주소를 처리
	public void logoutPost() {
		
	}
}
























