package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.daum.dao.MemberDAOImpl;
import net.daum.vo.MemberVO;

public class MemberEditController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");  		
		//브라우저로 출력되는 문자/태그, 언어코딩 타입을 설정
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		MemberDAOImpl mdao = new MemberDAOImpl();
		
		String id = (String) session.getAttribute("id"); //세션 아이디를 구함
		
		if(id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요');");
			out.println("location='member_login.do';");
			out.println("</script>");
		}else {
			String[] phone= {"010","011"};
			String[] email = {"naver.com", "daum.net", "nate.com","google.co.kr","직접입력"};
			//폰과 이메일 배열을 생성
			
			request.setAttribute("phone", phone); //phone 속성 키이름에 Object타입으로 Phone배열값을 저장
			request.setAttribute("email", email); //속성 키이름과 값 객체는 같게한다.
			
			MemberVO m = mdao.getMember(id); //오라클 DB로부터 id 에 해당하는 회원정보 가져오기
			
			request.setAttribute("m", m);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./view/member/member_edit.jsp"); //뷰페이지 경로 설정
			return forward;
		}//if else
		return null;
	}

}
