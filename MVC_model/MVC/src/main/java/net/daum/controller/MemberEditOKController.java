package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.daum.dao.MemberDAOImpl;
import net.daum.vo.MemberVO;

public class MemberEditOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인하세요')");
			out.println("</script>");
		}
		else {
			request.setCharacterEncoding("UTF-8"); //post방식으로 전달된 한글 데이터를 안깨지게 한다.
			
			String mem_pwd= request.getParameter("mem_pwd");
			String mem_name= request.getParameter("mem_name");
			String mem_zip= request.getParameter("mem_zip");
			String mem_zip2= request.getParameter("mem_zip2");
			String mem_addr= request.getParameter("mem_addr");
			String mem_addr2= request.getParameter("mem_addr2");
			String mem_phone01= request.getParameter("mem_phone01");
			String mem_phone02= request.getParameter("mem_phone02");
			String mem_phone03= request.getParameter("mem_phone03");
			String mail_id= request.getParameter("mail_id");
			String mail_domain= request.getParameter("mail_domain");
			
			MemberDAOImpl mado = new MemberDAOImpl();
			
			MemberVO m = new MemberVO();
			m.setMem_id(id);
			m.setMem_pwd(mem_pwd);
			m.setMem_name(mem_name);
			m.setMem_zip(mem_zip);	m.setMem_zip2(mem_zip2);
			m.setMem_addr(mem_addr);m.setMem_addr2(mem_addr2);
			m.setMem_phone01(mem_phone01);	m.setMem_phone02(mem_phone02);	m.setMem_phone03(mem_phone03);
			m.setMail_id(mail_id);	m.setMail_domain(mail_domain);
			
			mado.updateMember(m);
						
			out.println("<script>");
			out.println("alert('회원 정보 수정 완료!!');");
			out.println("location='member_edit.do';");
			out.println("</script>");
		}
		return null;
	}

}
