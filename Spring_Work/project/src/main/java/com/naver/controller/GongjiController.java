package com.naver.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.GongjiService;
import com.naver.vo.GongjiVO;

@Controller
public class GongjiController {

	@Inject
	private GongjiService gongjiService;

	//메인화면 최신 공지 목록 5개 보기(사용자 공지사항)
	@RequestMapping("/gongji_list")
	public String gongji_list(Model gm) {
		List<GongjiVO> glist=this.gongjiService.getList();
		gm.addAttribute("glist",glist);
		return "gongji/gongji_list";
	}//gongji_list()

	//공지내용보기+조회수 증가
	@RequestMapping("/gongji_cont")
	public ModelAndView gongji_cont(
			@RequestParam("gongji_no") int gongji_no)
	{
		GongjiVO g=this.gongjiService.getGCont(gongji_no);
		String g_cont=g.getGongji_cont().replace("\n","<br/>");

		ModelAndView gm=new ModelAndView("gongji/gongji_cont");
		gm.addObject("g",g);
		gm.addObject("g_cont",g_cont);
		return gm;
	}//gongji_cont()

}



















