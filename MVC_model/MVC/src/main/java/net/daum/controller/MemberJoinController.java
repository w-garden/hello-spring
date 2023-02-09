package net.daum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberJoinController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String[] phone= {"010","011"};
		String[] email = {"naver.com", "daum.net", "nate.com","google.co.kr","직접입력"};
		//폰과 이메일 배열을 생성
		
		request.setAttribute("phone", phone); //phone 속성 키이름에 Object타입으로 Phone배열값을 저장
		request.setAttribute("email", email);
		//속성 키이름과 값 객체는 같게한다.
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); //기존 매핑주소값을 유지하고, 속성 키이름과 값도 유지한다
		forward.setPath("./view/member/member_join.jsp");
		return forward;
	}

}
