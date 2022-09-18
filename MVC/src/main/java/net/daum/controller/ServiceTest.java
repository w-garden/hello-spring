package net.daum.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceTest extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//get or post 방식에 상관없이 service() 메서드를 호출해서 화면에 보여지는 뷰페이지(view)로 이동
		
		
		RequestDispatcher forward=request.getRequestDispatcher("./view/test/g_write.jsp"); 
		//컨트롤러를 통해서 view 폴더 하위의 test 폴더 하위의 g_write.jsp로 이동
		forward.forward(request, response);

	}

}
