package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daum.dao.BoardDAOImpl;
import net.daum.vo.BoardVO;

public class BoardDeLOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int page =1;
		if(request.getParameter("page") != null) {
			page= Integer.parseInt(request.getParameter("page"));
		}
		
		String board_pwd=request.getParameter("del_pwd");
		
		BoardDAOImpl bdao = new BoardDAOImpl();
		BoardVO db_pwd=bdao.getBoardCont(board_no);
		
		if(!db_pwd.getBoard_pwd().equals(board_pwd)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			
			bdao.deleteBoard(board_no);
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("board_list.do?page="+page);
			return forward;
		}
		return null;
	}

}
