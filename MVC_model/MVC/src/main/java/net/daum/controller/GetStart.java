package net.daum.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getBegin" )
public class GetStart extends HttpServlet {
	/*
	 * 서블릿 자바 특징 )
	 * 1. 서블릿 클래스를 만드려면 HttpServlet으로 상속받는다
	 * 2. 인터넷 상에서 누구나 다 접근 가능 할 수 있게 public 접근권한지정자로 선언
	 * 3. 주소창에서 직접 실행하는 방식은 get방식이다. get으로 접근할 때는 doGet() 메서드를 호출한다
	 * 4. HttpServletRequest 서블릿은 사용자폼에서 입력한 정보를 서버로 가져오는 역할을 한다.
	 * 5. HttpServletResponse는 서버의 가공된 정보를 사용자 웹브라우저로 응답할 때 사용한다
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//웹브라우저에 응답하는 문자, 태그, 언어코딩 타입을 설정
		PrintWriter out = response.getWriter(); //출력 스트림 객체 out을 생성
		out.println("서블릿 최초 실행 ~");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
