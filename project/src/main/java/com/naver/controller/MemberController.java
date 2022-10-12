package com.naver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	//회원관리 로그인 폼페이지
	@GetMapping("member_login")
	public ModelAndView member_Login() {
		return new ModelAndView("member/member_Login");
	}//member_Login()
	
	
	
	//사용자 회원가입폼
	   @RequestMapping("member_join")
	   public String member_join(Model m) {
	      String[] phone = {"010","011","019"};
	      String[] email = {"naver.com","daum.net","nate.com","google.com","직접입력"};
	      m.addAttribute("phone", phone);
	      m.addAttribute("email", email);
	      return "member/member_join"; //뷰리졸브 경로 설정(뷰페이지 경로 설정)
	   }//member_join()
	
}
