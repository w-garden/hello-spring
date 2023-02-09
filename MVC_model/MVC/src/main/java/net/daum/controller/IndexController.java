package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.daum.dao.MemberDAOImpl;
import net.daum.vo.MemberVO;
//로그인 인증 이후 메인화면으로 이동
public class IndexController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id= (String)session.getAttribute("id");

		if(id== null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!)");
			out.println("location='member_login.do';");
			out.println("</script>");
		}else {
			MemberDAOImpl mdao = new MemberDAOImpl();
			MemberVO m = mdao.loginCheck(id);
			request.setAttribute("profile", m.getMem_file()); //profile속성 키이름에 첨부파일 경로를 저장
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./view/member/member_Login.jsp");
			return forward;
		}
		
		return null;
	}

}
