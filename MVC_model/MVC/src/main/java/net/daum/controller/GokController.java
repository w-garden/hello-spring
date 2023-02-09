package net.daum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daum.dao.GuDAOimpl;
import net.daum.vo.GuVO;

@WebServlet("/g_ok") //g_ok URL 매핑주소 등록
public class GokController extends HttpServlet {
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post 방식으로 접근할때는 doPost ()메서드를 호출한다
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out =response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String title=request.getParameter("title");
		String cont = request.getParameter("cont");
		
		//out.println("제목 :" + title +"<hr>");
		//out.println("내용 :" + cont +"<hr>");
		
		GuDAOimpl gdao = new GuDAOimpl();
		GuVO g=new GuVO();
		
		g.setTitle(title); g.setCont(cont);
		
		int re = gdao.insertGu(g); //방명록저장
		
		if(re==1) {
			out.println("<script> alert ('방명록 저장 성공'); ");
			out.println("location='glist';"); //목록 보기 매핑으로 이동
			out.println("</script>");
			
		}else {
			out.println("실패");

		}
		out.close();
		
	}

}
