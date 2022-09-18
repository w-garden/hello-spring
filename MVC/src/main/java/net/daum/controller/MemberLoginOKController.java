package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.daum.dao.MemberDAOImpl;
import net.daum.vo.MemberVO;

public class MemberLoginOKController implements Action {
/* 로그인 인증 컨트롤러 클래스 : 아이디를 기준으로 회원정보를 검색한 다음 회원정보가 있는 경우는 비번을 비교해서 비번이 같은 경우만
 * 로그인 인증 처리한다. 로그인 인증했을때 세션을 사용한다.
 * 
 * 
 */
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("login_id");
		String pwd= request.getParameter("login_pw");
		
		MemberDAOImpl mdao = new MemberDAOImpl();
		MemberVO dm =mdao.loginCheck(id);//로그인 인증처리
		
		if(dm==null) {
			out.println("<script>");
			out.println("alert('회원정보가 검색되지 않습니다')");
			out.println("history.go(-1);");
			out.println("</script>");
		}
		else {
			if(!dm.getMem_pwd().equals(pwd)) {
				out.println(dm.getMem_pwd()+"<br>");
				out.println(pwd);


				out.println("<script>");
				out.println("alert('비밀번호가 일치하지 않습니다')");
				out.println("history.back();");
				out.println("</script>");
			}
			else { //비번이 같아서 로그인 인증된경우
				HttpSession session = request.getSession(); //세션 객체 생성
				session.setAttribute("id", id); //세션 id에 아이디 저장
				
				ActionForward forward = new ActionForward();
				forward.setRedirect(true); //새로운 매핑 주소로 이동
				forward.setPath("index.do");
				return forward;
			
			}
		}
		return null;
	}

}
