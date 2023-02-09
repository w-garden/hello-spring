package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.daum.dao.MemberDAOImpl;
import net.daum.vo.MemberVO;

public class MemberDeLOKController implements Action {

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
			request.setCharacterEncoding("UTF-8");
			String del_pwd= request.getParameter("del_pwd");
			String del_cont = request.getParameter("del_cont");
			
			MemberDAOImpl mdao = new MemberDAOImpl();
			MemberVO db_pwd = mdao.getMember(id);
			
			if(!db_pwd.getMem_pwd().equals(del_pwd)) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
			}
			else {
				MemberVO dm = new MemberVO();
				dm.setMem_id(id); dm.setMem_delcont(del_cont);
				mdao.deleteMember(dm); //아이디를 기준으로 탈퇴사유, mem_state=2, 탈퇴날짜를 수정되게 하면서 탈퇴시킨다.
				
				session.invalidate();
				out.println("<script>");
				out.println("alert('회원 탈퇴 성공!');");
				out.println("location='member_login.do';");
				out.println("</script>");
			}
		
		
		}
		
		
		return null;
	}

}
