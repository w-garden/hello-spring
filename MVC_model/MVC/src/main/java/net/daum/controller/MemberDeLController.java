package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.daum.dao.MemberDAOImpl;
import net.daum.vo.MemberVO;

public class MemberDeLController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");  		
		//브라우저로 출력되는 문자/태그, 언어코딩 타입을 설정
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id"); //세션 아이디를 구함
		
		if(id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요');");
			out.println("location='member_login.do';");
			out.println("</script>");
		}else {
			MemberDAOImpl mdao = new MemberDAOImpl();
			MemberVO dm = mdao.getMember(id);
			
			request.setAttribute("dm", dm);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false); 
			forward.setPath("./view/member/member_del.jsp");
			return forward;
		}
		return null;
	}

}
