package net.daum.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daum.dao.BoardDAOImpl;
import net.daum.vo.BoardVO;

public class BoardEditOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("UTF-8");
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		String board_name=request.getParameter("board_name");
		String board_pwd=request.getParameter("board_pwd");
		String board_title=request.getParameter("board_title");
		String board_cont=request.getParameter("board_cont");
		
		BoardDAOImpl bdao = new BoardDAOImpl();
		BoardVO db_pwd=bdao.getBoardCont(board_no);
		
		if(!db_pwd.getBoard_pwd().equals(board_pwd)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			BoardVO eb = new BoardVO();
			eb.setBoard_no(board_no); eb.setBoard_name(board_name); eb.setBoard_cont(board_cont);
			eb.setBoard_title(board_title);
			
			bdao.updateBoard(eb);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("board_cont.do?board_no="+board_no+"&page="+page+"&state=cont");
			return forward;
		}
		return null;
	}

}
