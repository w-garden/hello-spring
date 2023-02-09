package net.daum.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daum.dao.GuDAOimpl;
import net.daum.vo.GuVO;

/* 연습용 서블릿 방명록 MVC의 목록 보기 컨트롤러
 * 
 * 
 */
@WebServlet("/glist") // /glist 매핑 주소
public class GListController extends HttpServlet {
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get 으로 접근할때는 doGet() 메서드를 호출한다
		
		GuDAOimpl gdao = new GuDAOimpl();
		List<GuVO> glist = gdao.getGuList();
		
		request.setAttribute("glist", glist);
		
		RequestDispatcher listForward = request.getRequestDispatcher("./view/test/g_list.jsp"); //뷰 페이지 경로 설정
		listForward.forward(request, response);
		/* request.setAttribute("glist", glist) 로 저장된 glist 키값을 유지하려면
		 * forward() 메서드로 이동해야 한다. sendRedirect() 로 이동하면 기본 url 매핑주속밧도 잃어버리고
		 * request로 저장된 속성 키 값도 유지하지 못함
		 * 이유는 새로운 매핑주소로 이동하기 때문이다
		 */
		
		
	}

}
