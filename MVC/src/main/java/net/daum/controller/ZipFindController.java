package net.daum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ZipFindController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./view/member/zip_find.jsp");
		return forward;
		
		
	}

}
