package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogOutController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자 태그, 언어코딩 타입을 설정
		PrintWriter out=response.getWriter(); //출력 스트림 객체 생성
		HttpSession session = request.getSession(); //세션 객체 생성
		
		session.invalidate(); //세션만료 =>로그아웃
		
		out.println("<script>");
		out.println("alert('로그아웃 되었습니다.')");
		out.println("location='member_login.do'");
		out.println("</script>");
		out.close();

		return null;
		
	}

}
