package com.naver.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.AdminService;
import com.naver.vo.AdminVO;

import pwdconv.PwdChange;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	//관리자 로그인폼
	@GetMapping("admin_index")
	public String admin_index() {
		return "admin/admin_login"; 
	}
	
	//관리자 로그인 인증과 관리자 비번 암호화
	@PostMapping("admin_login_ok")
	public ModelAndView admin_login_ok(AdminVO ab, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ab.setAdmin_pwd(PwdChange.getPassWordToXEMD5String(ab.getAdmin_pwd())); //비번 암호화
		//ab.setAdmin_name("관리자");
		//this.adminService.insertAdmin(ab); //관리자 정보 저장(관리자 아이디, 암호화 된 관리자 비번, 관리자 이름등 )
		
		AdminVO admin_pwd=this.adminService.adminLogin(ab.getAdmin_id()); //관리자 아이디를 기준으로 관리자 정보를 DB로부터 가져옴
		
		
		if(admin_pwd == null) {
			out.print("<script>");
			out.print("alert('관리자 정보가 없습니다')");
			out.print("history.back();");
			out.print("</script>");
		}else {
			if(!admin_pwd.getAdmin_pwd().equals(ab.getAdmin_pwd())) {
				out.print("<script>");
				out.print("alert('관리자 비번이 다릅니다')");
				out.print("history.back();");
				out.print("</script>");
			}else {
				session.setAttribute("admin_id", ab.getAdmin_id());
				session.setAttribute("admin_name", ab.getAdmin_name());
				return new ModelAndView("redirect:/admin_main");
			}
		}
		return null;
	}//admin_login_ok
	
}
