package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daum.dao.MemberDAOImpl;
import net.daum.vo.MemberVO;

public class PwdFindOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		//브라우저로 출력되는 문자/태그, 언어코딩 타입을 설정
		
		PrintWriter out = response.getWriter();
		
		MemberDAOImpl mdao = new MemberDAOImpl();
		MemberVO m = new MemberVO();
		
		request.setCharacterEncoding("UTF-8"); //method=post로 전달되는 한글 데이터를 안깨지게 한다.
		
		String pwd_id= request.getParameter("pwd_id");
		String pwd_name= request.getParameter("pwd_name");
		
		m.setMem_id(pwd_id); m.setMem_name(pwd_name);
		MemberVO pm =mdao.pwdMember(m);
		
		if(pm==null) {
			out.println("<script>");
			out.println("alert('회원정보를 찾을 수 없습니다!');");
			out.println("hostory.back();"); //뒤로 한칸이동
			out.println("</script>");
		}
		else {
			request.setAttribute("find_pwd", pm.getMem_pwd());
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./view/member/pwd_find_ok.jsp");
			return forward;
		}
		
		return null;
		
	}

}
