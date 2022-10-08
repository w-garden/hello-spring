package net.daum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.daum.service.BoardExService;
import net.daum.vo.Tbl_boardEX;

@Controller
public class BoardExController {
	@Autowired
	BoardExService boardExService;
	
	@RequestMapping(value="boardEx_list")
	public String boardEx_list(Model model, HttpServletRequest request, Tbl_boardEX tb) {
		
		int page =1;
		int limit =10;
		if(request.getParameter("page")!=null) 
			page=Integer.parseInt(request.getParameter("page"));
		
		tb.setStartrow((page-1)*10 +1);
		tb.setEndrow(tb.getStartrow()+ limit -1);
		
		int count = boardExService.getCount();
		List<Tbl_boardEX> tlist=boardExService.getALLBoard(tb);
		
		int maxpage = (int)((double) count / limit +0.95);
		int startpage = (((int)((double)page /10 +0.9))-1) *10 +1;
		int endpage = maxpage;
		
		if(endpage > startpage +10 -1)
			endpage = startpage +10-1;

		model.addAttribute("tlist",tlist);
		model.addAttribute("count",count);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("page",page);
		
		return "boardEx_list";
	}
}
