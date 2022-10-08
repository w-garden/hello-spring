package com.naver.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		ab.setAdmin_pwd(PwdChange.getPassWordToXEMD5String(ab.getAdmin_pwd())); //비번 암호화 : 과거 네이트온에서 사용한 MD5 암호화 인코딩 스킬
		//ab.setAdmin_name("관리자");
		//this.adminService.insertAdmin(ab); //관리자 정보 저장(관리자 아이디, 암호화 된 관리자 비번, 관리자 이름등 )
		
		AdminVO admin_pwd=this.adminService.adminLogin(ab.getAdmin_id()); //관리자 아이디를 기준으로 관리자 정보를 DB로부터 가져옴
		
		
		if(admin_pwd == null) {
			out.println("<script>");
			out.println("alert('관리자 정보가 없습니다')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			if(!admin_pwd.getAdmin_pwd().equals(ab.getAdmin_pwd())) {
				out.println("<script>");
				out.println("alert('관리자 비번이 다릅니다')");
				out.println("history.back();");
				out.println("</script>");
			}else {
				session.setAttribute("admin_id", ab.getAdmin_id());
				session.setAttribute("admin_name", admin_pwd.getAdmin_name());
				return new ModelAndView("redirect:/admin_main"); //redirect 는 get 방식이다. admin_main 은 매핑주소
			}
		}
		return null;
	}//admin_login_ok
	
	//관리자 메인
	@RequestMapping("/admin_main")
	public String admin_main(HttpServletResponse response, HttpSession session) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String admin_id = (String) session.getAttribute("admin_id");//관리자 세션 아이디값을 구함
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요')");
			out.println("location='admin_index';");
			out.println("</script>");
		}
		else {
			return "admin/admin_main"; //뷰페이지 경로는 /WEB-INF/view/admin/admin_main.jsp
		}
		return null;
	}//admin_main
	
	//관리자 로그아웃
	@RequestMapping(value="/admin_logout", method=RequestMethod.POST)
	public String admin_logout(HttpServletResponse response, HttpSession session) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		session.invalidate();
		
		out.println("<script>");
		out.println("alert('관리자 로그아웃 되었습니다!')");
		out.println("location='admin_index';");
		out.println("</script>");
		
		return null;
	}//admin_logout
	
}
