package net.daum.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daum.dao.BoardDAOImpl;
import net.daum.vo.BoardVO;

public class BoardReplyOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		//답변글 히든값3개 받기
		int board_ref=Integer.parseInt(request.getParameter("board_ref"));
		int board_step=Integer.parseInt(request.getParameter("board_step"));
		int board_level=Integer.parseInt(request.getParameter("board_level"));
		
		
		//페이징에서 책갈피 기능 구현을 위한 쪽번호를 받는다.
		int page =Integer.parseInt(request.getParameter("page"));
		
		String board_name=request.getParameter("board_name");
		String board_title=request.getParameter("board_title");
		String board_pwd=request.getParameter("board_pwd");
		String board_cont = request.getParameter("board_cont");
		
		BoardVO rb = new BoardVO();
		rb.setBoard_name(board_name);
		rb.setBoard_title(board_title);
		rb.setBoard_pwd(board_pwd);
		rb.setBoard_cont(board_cont);
		rb.setBoard_ref(board_ref);
		rb.setBoard_step(board_step);
		rb.setBoard_level(board_level);
		
		BoardDAOImpl bdao = new BoardDAOImpl();
		bdao.replyBoard(rb); //답변 저장
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("board_list.do?page="+page);
		return forward;
	}

}
