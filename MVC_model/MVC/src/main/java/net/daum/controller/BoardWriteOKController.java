package net.daum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daum.dao.BoardDAOImpl;
import net.daum.vo.BoardVO;

public class BoardWriteOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String board_name=request.getParameter("board_name");
		String board_title=request.getParameter("board_title");
		String board_pwd=request.getParameter("board_pwd");
		String board_cont=request.getParameter("board_cont");
		
		BoardVO b = new BoardVO();
		b.setBoard_name(board_name); b.setBoard_title(board_title); b.setBoard_pwd(board_pwd);
		b.setBoard_cont(board_cont);
		
		BoardDAOImpl bdao = new BoardDAOImpl();
		bdao.insertBoard(b);
		
		ActionForward forward =  new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_list.do");
 		return forward;
	}

}
