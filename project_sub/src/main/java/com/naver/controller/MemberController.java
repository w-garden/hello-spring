package com.naver.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.MemberService;
import com.naver.vo.MemberVO;
import com.naver.vo.ZipcodeVO;
import com.naver.vo.ZipcodeVO2;

import pwdconv.PwdChange;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	// 회원관리 로그인 폼페이지
	@GetMapping("member_login")
	public ModelAndView member_Login() {
		return new ModelAndView("member/member_Login");
	}// member_Login()

	// 사용자 회원가입폼
	@RequestMapping("member_join")
	public String member_join(Model m) {
		String[] phone = { "010", "011", "019" };
		String[] email = { "naver.com", "daum.net", "nate.com", "google.com", "직접입력" };
		m.addAttribute("phone", phone);
		m.addAttribute("email", email);
		return "member/member_join"; // 뷰리졸브 경로 설정(뷰페이지 경로 설정)
	}// member_join()

	// 아이디 중복 검색
	@PostMapping("/member_idcheck")
	public String member_idcheck(String id, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		MemberVO db_id = this.memberService.idCheck(id); // 아이디에 해당 회원정보를 DB로부터 검색
		int re = -1; // 아이디가 없을때 -1을 반환

		if (db_id != null) { // 중복 아이디가 있는 경우
			re = 1;
		}
		out.println(re); // 값 반환
		return null;
	}// member_idCheck()

	// 우편주소 검색 공지창
	@RequestMapping(value = "/zip_find", method = RequestMethod.GET)
	public ModelAndView zip_find() {
		ModelAndView zm = new ModelAndView("/member/zip_find");
		return zm;
	}

	// 우편검색 결과
	@PostMapping("/zip_find_ok")
	public ModelAndView zip_find_ok(String dong) {
		List<ZipcodeVO> zlist = memberService.zipFind("%" + dong + "%");

		List<ZipcodeVO2> zlist2 = new ArrayList<ZipcodeVO2>();
		for (ZipcodeVO z : zlist) {
			ZipcodeVO2 z2 = new ZipcodeVO2();
			z2.setZipcode(z.getZipcode());
			z2.setAddr(z.getSido() + " " + z.getGugun() + " " + z.getDong());
			zlist2.add(z2);
		}

		ModelAndView zm = new ModelAndView("member/zip_find");
		zm.addObject("zipcodelist", zlist2);
		zm.addObject("dong", dong);
		return zm;
	}// zip_find_ok()

	// 회원저장
	@PostMapping("/member_join_ok")
	public String member_join_ok(MemberVO m) {
		m.setMem_pwd(PwdChange.getPassWordToXEMD5String(m.getMem_pwd())); // 비번을 암호화
		memberService.insertMember(m); // 회원 저장

		return "redirect:/member_login";
	}// member_join_ok

	// 비번찾기 공지창
	@GetMapping("/pwd_find")
	public String pwd_find() {
		return "member/pwd_find";
	}// pwd_find()

	// 비번찾기결과
	@PostMapping("/pwd_find_ok")
	public String pwd_find_ok(@RequestParam("pwd_id") String pwd_id, String pwd_name, HttpServletResponse response,
			MemberVO m, Model fm) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		m.setMem_id(pwd_id);
		m.setMem_name(pwd_name);

		MemberVO pm = memberService.pwdMember(m);
		if (pm == null) {
			out.println("<script>");
			out.println("alert('회원으로 검색되지 않습니다. \\n올바른 회원정보를 입력하세요!');");
			out.println("history.go(-1);");
			out.println("</script>");
		} else {
			Random r = new Random();
			int pwd_random = r.nextInt(100000); // 0이상 십만미만 사이의 정수 숫자 난수를 발생
			String ran_pwd = Integer.toString(pwd_random); // 임시 정수 비번을 문자열로 변경
			m.setMem_pwd(PwdChange.getPassWordToXEMD5String(ran_pwd)); // 임시 비번 암호화
			this.memberService.updatePwd(m); // 임시비번 수정

			fm.addAttribute("ran_pwd", ran_pwd); // 임시비번을 ran_pwd 키이름에 저장
			return "member/pwd_find_ok"; // 뷰페이지 경로(뷰리졸브 경로)
		}
		return null;
	}// pwd_find_ok()

	// 로그인 인증 처리
	@RequestMapping("/member_login_ok")
	public ModelAndView member_login_ok(String login_id, String login_pwd, HttpSession session,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		MemberVO m = memberService.loginCheck(login_id); // 로그인 인증처리

		if (m == null) {
			out.println("<script>");
			out.println("alert('가입정보가 없습니다');");
			out.println("history.go(-1);");
			out.println("</script>");
		} else {
			if (!m.getMem_pwd().equals(PwdChange.getPassWordToXEMD5String(login_pwd))) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다');");
				out.println("history.go(-1);");
				out.println("</script>");
			} else {
				session.setAttribute("id", login_id); // 세션 id키이름에 아이디 저장
				return new ModelAndView("redirect:/index");// 로그인 인증 후 메인화면으로 이동
			} // if else
		}
		return null;
	}// member_login_ok

	// 로그인 인증후 메인화면
	@GetMapping("/index")
	public String index(HttpServletResponse response, HttpSession session) throws Exception {
		response.setContentType("text/html; charset=UTf-8");

		if (isLogin(response, session)) {
			return "member/member_Login";
		}
		return null;
	}// index()

	@PostMapping("/member_logout")
	public String member_logout(HttpSession session, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTf-8");
		PrintWriter out = response.getWriter();

		session.invalidate();
		out.println("<script>");
		out.println("alert('로그아웃 되었습니다!');");
		out.println("location='member_login';");
		out.println("</script>");
		
		return null;
	}//member_logout()

	
	
	//정보수정폼
	@RequestMapping("member_edit")
	public ModelAndView member_edit(HttpServletResponse response, HttpSession session) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String id = (String) session.getAttribute("id");
		PrintWriter out = response.getWriter();
		
		if (id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요!');");
			out.println("location='member_login';");
			out.println("</script>");
		} else {
			String[] phone=  {"010", "011","019"};
			String[] email=  {"naver.com", "daum.net","gmail.com","직접입력"};
			
			
			MemberVO em = memberService.getMember(id); //아이디에 해당하는 회원정보를 DB로부터 가져옴
			
			
			ModelAndView mv= new ModelAndView();
			mv.addObject("m",em);
			mv.addObject("phone", phone);
			mv.addObject("email", email);
			mv.setViewName("member/member_Edit");
			return mv;
		}
		return null;
	}
	
	// 반복적인 로그인 확인을 하나로 줄이기
	public static Boolean isLogin(HttpServletResponse response, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		PrintWriter out = response.getWriter();
		if (id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login';");
			out.println("</script>");
			return false;
		}
		return true; // 로그인된 경우는 true 를 반환
	}// isLogin()
}
