package net.daum.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daum.controller.Action;
import net.daum.controller.ActionForward;
import net.daum.dao.BoardDAOImpl;
import net.daum.vo.BoardVO;

public class BoardContController implements Action {
	/* 내용보기, 답변글, 수정폼, 삭제폼
	 * 4개의 부분이 실행됨.
	 */
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		String state = request.getParameter("state");
		
		int page =1;
		if(request.getParameter("page")!= null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		BoardDAOImpl bdao = new BoardDAOImpl();
		if(state.equals("cont")) {
			bdao.updateHit(board_no);
		}
		BoardVO bc = bdao.getBoardCont(board_no); //번호에 해당하는 DB레코드값 가져옴
		String board_cont=bc.getBoard_cont().replace("\n", "<br>");
		
		request.setAttribute("bc", bc);
		request.setAttribute("page", page); //책갈피 기능
		request.setAttribute("board_cont", board_cont);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		if(state.equals("cont")) {//내용보기
			forward.setPath("./view/board/board_cont.jsp");
		}
		else if(state.equals("reply")) {//답변글 폼
			forward.setPath("./view/board/board_reply.jsp");
		}
		else if(state.equals("edit")) {//수정폼
			forward.setPath("./view/board/board_edit.jsp");
		}
		else if(state.equals("del")) {//삭제폼
			forward.setPath("./view/board/board_del.jsp");
		}
		return forward;
	}

}
