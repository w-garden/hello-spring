package net.daum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.daum.service.BoardService;
import net.daum.vo.BoardVO;

@Controller
@RequestMapping("/board/*") // 컨트롤 자체 매핑주소

public class BoardController {

	@Autowired // 자동의존성
	private BoardService boardService;

	// 스프링 MVC게시판 글쓰기 폼
	@RequestMapping(value = "/board_write", method = RequestMethod.GET) // GET으로 접근하는 board_write 매핑주소를 처리
	public void board_write(HttpServletRequest request) { // void 형이면 매핑 주소가 뷰페이지 파일명이 된다.
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("page", page); // page키 이름에 쪽번호 저장 => 페이징에서 책갈피기능을 구현
	}

	// 게시물저장
	@RequestMapping(value = "/board_write", method = RequestMethod.POST) // post 로 접근하는 매핑주소 처리, 폼태그에서 액션 속성을 지정하지 않으면
																			// 이전 매핑주소가
	// 액션 매핑주소가 된다. 같은 매핑 주소 구분은 method인 get or post 로 구분
	public ModelAndView board_write_ok(BoardVO b, RedirectAttributes rttr) {
		/*
		 * BoardVO b는 board_write.jsp 의 네임파라미터 이름과 BoardVO 빈클래스의 변수명이 같으면 b 객체에 글쓴이와 제목,
		 * 글내용이 저장되어 있다. 코드라인이 줄이는 효과가 발생한다.
		 */
		this.boardService.insertBoard(b);// 게시물 저장
		rttr.addFlashAttribute("msg", "SUCCESS"); // msg 키이름에 SUCCESS문자열로 담아서 서버 컨트롤의 다른 매핑주소로 전달한다. 이 방법은 웹주소창에서 노출되지
													// 않아서 보안상 좋다.
		return new ModelAndView("redirect:/board/board_list"); // get 방식으로 다른 매핑 주소인 목록보기로 이동
	}// board_write_ok
	// 게시물 목록

	@GetMapping("/board_list") // get으로 접근하는 매핑주소 board_lsit 등록
	public String board_list(Model m, HttpServletRequest request, BoardVO b) {

		int page = 1;// 현재 쪽번호
		int limit = 4;// 한페이지 보여지는 목록개수
		if (request.getParameter("page") != null) {
			// get으로 전달된 쪽번호가 있는 경우 실행
			page = Integer.parseInt(request.getParameter("page"));
			// 쪽번호를 정수 숫자로 바꿔서 저장
		}
		b.setStartrow((page - 1) * 4 + 1);// 시작행번호
		b.setEndrow(b.getStartrow() + limit - 1);// 끝행번호

		int listCount = this.boardService.getListCount();
		// 총게시물수
		List<BoardVO> blist = this.boardService.getBoardList(b);
		// 게시물 목록

		// 총페이지수
		int maxpage = (int) ((double) listCount / limit + 0.95);
		// 현재페이지에 보여질 시작페이지
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여질 마지막 페이지
		int endpage = maxpage;

		if (endpage > startpage + 10 - 1)
			endpage = startpage + 10 - 1;
		// 마지막 페이지> 시작페이지+10-1 마지막페이지=시작페이지+10-1

		m.addAttribute("list", blist);// list 키이름에
		// blist 를 저장
		m.addAttribute("totalCount", listCount);
		// totalCount 키이름에 listCount저장
		m.addAttribute("startpage", startpage);
		m.addAttribute("endpage", endpage);
		m.addAttribute("maxpage", maxpage);
		m.addAttribute("page", page);// page키이름에 쪽번호 //를 저장
		
		
		return "board/board_list"; //뷰 리졸브 경로(뷰페이지 경로) => /WEB-INF/views/board/board_list.jsp
	}// board_list();
	
	
	//내용보기와+조회수 증가 
	@RequestMapping("board_cont") //get or post 방식일 때 모두 실행 
	public ModelAndView board_cont(@RequestParam("bno") int bno, @RequestParam("page") int page) {
		/* @RequestParam("bno") 에노테이션은 서블릿의 request.getParameter("bno")와 같은 기능을 한다. 결국 bno, page 네임파라미터 
		 * 이름에 담겨진 값을 가져온다. 
		 * */
		BoardVO b = this.boardService.getBoardCont(bno); //조회수 증가와 내용보기
		String cont = b.getContent().replace("\n", "<br>");
		ModelAndView cm = new ModelAndView();
		cm.addObject("b", b); //b 키이름에 b 객체를 저장
		cm.addObject("cont",cont);
		cm.addObject("page", page); //책갈피 기능 구현을 위한 쪽번호 저장
		cm.setViewName("board/board_cont2");
		return cm;
		
	}//board_cont();
	
	
	//게시물 수정폼
	@RequestMapping("board_edit")
	public String board_edit(int bno, int page, HttpServletRequest request) {
		//int bno, int page로 지정하면 get으로 전달된 bno, page파라미터 값을 가져옴
		BoardVO eb = boardService.getBoardCont2(bno); //조회수를 증가하지 않고 번호에 해당하는 레코드를 가져오기
		request.setAttribute("eb", eb);
		request.setAttribute("page", page);
		return "board/board_edit"; //뷰리졸브 경로가 WEB-INF/views/board/board_edit.jsp
		
	}
	//수정완료
	@RequestMapping("board_edit_ok") //get or post 양쪽방식으로 접근하는 board_edit_ok 매핑주소 처리
	public ModelAndView board_edit_ok(int bno, int page, BoardVO eb) {
		/* get으로 전달된 번호와 쪽번호는 int bno, int page로 받고, 수정할 글쓴이, 제목, 글내용은 post방식으로 전달된것은 BoardVO eb로 받는다.
		 * eb로 받으려고 네임파라미터 이름과 빈클래스 변수명을 갖게 하였다.
		 * 
		 */
		eb.setBno(bno); //번호 값을 저장
		this.boardService.updateBoard(eb);
		
		ModelAndView em = new ModelAndView();
		em.setViewName("redirect:/board/board_cont"); //매핑주소 경로
		em.addObject("bno", bno);
		em.addObject("page", page);
		return em; //주소창에 매핑주소가 다음과 같이 설정된다. /board/board_cont?bno=번호&page=쪽번호  (get방식)
		//수정한 값을 확인할 수 있다.
	}//board_edit_ok()
	
	//게시물 삭제
	@GetMapping("board_del")	//board_del 매핑주소 등록, get으로 접근하는 매핑주소를 리턴
	public ModelAndView board_del(int bno, int page, RedirectAttributes rttr) {
		//int bno, int page하면 파라미터네임, bno, page로 전달된 번호값과 쪽번호를 가져온다
		
		boardService.deleteBoard(bno); //게시물 삭제
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		
		ModelAndView dm = new ModelAndView();
		dm.setViewName("redirect:/board/board_list?page=" + page);
		return dm; //    /board/board_list?page=쪽번호 가 get으로 전달
		
		
		
		
	}//board_del()
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
