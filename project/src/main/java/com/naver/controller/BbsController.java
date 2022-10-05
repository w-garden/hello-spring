package com.naver.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.BbsService;
import com.naver.vo.BbsVO;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class BbsController {
	
	@Autowired
	private BbsService bbsService;
	
	//자료실 글 입력폼
	@GetMapping("/bbs_write")
	public ModelAndView bbs_write(HttpServletRequest request) {
		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		ModelAndView wm = new ModelAndView("bbs/bbs_write"); //생성자 인자값으로 뷰페이지(뷰리졸브) 경로 설정
		wm.addObject("page",page);
		return wm;
	}
	
	
	//자료실 저장
	@PostMapping("/bbs_write_ok")
	public String bbs_write_ok(BbsVO b, HttpServletRequest request) throws IOException {
		String saveFolder =request.getRealPath("/resources/upload");
		
		int fileSize=5*1024*1024;
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		
		String bbs_name = multi.getParameter("bbs_name");
		String bbs_title = multi.getParameter("bbs_title");
		String bbs_pwd = multi.getParameter("bbs_pwd");
		String bbs_cont = multi.getParameter("bbs_cont");
		
		File upFile = multi.getFile("bbs_file");
		
		if(upFile != null) {
			String fileName=upFile.getName();
			Calendar cal = Calendar.getInstance();
			int year =cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int date = cal.get(Calendar.DATE);
			
			String homedir = saveFolder+"/"+year+"-"+month+"-"+date;//오늘 날짜 폴더 경로 저장
			File path01 = new File(homedir);
			
			if(!(path01.exists())) {
				path01.mkdir(); //오늘날짜 폴더 생성
			}
			Random r = new Random(); //난수를 발생시키는 클래스
			int random = r.nextInt(1000000000);
			
			/*첨부 파일 확장자를 구함 */
			int index=fileName.lastIndexOf(".");
			String fileExtendsion = fileName.substring(index+1); //마침표 이후부터 마지막 문자까지 구함
			String refileName ="bbs"+year+month+date+random+"."+fileExtendsion;//새로운 파일명 문자열
			String fileDBname ="/"+year+"-"+month+"-"+date+"/"+refileName; //db에 저장될 파일명
			upFile.renameTo(new File(homedir+"/"+refileName)); //생성된 폴더에 변경된 파일명으로 실제 업로드
			
			b.setBbs_file(fileDBname);
		}else { //첨부파일이 없는 경우
			String fileDBName ="";
			b.setBbs_file(fileDBName);

		}
		b.setBbs_name(bbs_name); b.setBbs_title(bbs_title); b.setBbs_pwd(bbs_pwd);
		b.setBbs_cont(bbs_cont);
		
		bbsService.insertBbs(b); //자료실 저장
		
		return "redirect:/bbs_list"; //자료실 목록보기로 이동
				
	}//bbs_write_ok
	
	//자료실 목록
	
	@RequestMapping(value="/bbs_list", method=RequestMethod.GET)
	public String bbsList (Model listM, HttpServletRequest request, @ModelAttribute BbsVO b) {
		
		   int page=1;
		      int limit=10;//한페이지에 보여지는 목록개수
		      if(request.getParameter("page") != null) {
		page=Integer.parseInt(request.getParameter("page"));         
		      }
		      String find_name=request.getParameter("find_name");  //검색어
		      String find_field=request.getParameter("find_field");	//검색필드
		        b.setFind_field(find_field);
		        b.setFind_name("%"+find_name+"%");
		        //%는 검색에서 하나이상의 임의의 문자와 매핑 대응하는
		        //와일드 카드문자
		        
		      int totalCount=this.bbsService.getRowCount(b);
		      //총레코드 개수,검색후 레코드 개수
		      
		      b.setStartrow((page-1)*10+1);//시작행번호
		       b.setEndrow(b.getStartrow()+limit-1);//끝행 번호
		      
		      List<BbsVO> blist=this.bbsService.getBbsList(b);     //검색전후 목록
		      
		      //총 페이지수
		      int maxpage=(int)((double)totalCount/limit+0.95);
		      //시작페이지(1,11,21 ..)
		      int startpage=(((int)((double)page/10+0.9))-1)*10+1;
		      //현재 페이지에 보여질 마지막 페이지(10,20 ..)
		      int endpage=maxpage;
		      if(endpage>startpage+10-1) endpage=startpage+10-1;
		      
		      listM.addAttribute("blist",blist);
		      listM.addAttribute("page",page);
		      listM.addAttribute("startpage",startpage);
		      listM.addAttribute("endpage",endpage);
		      listM.addAttribute("maxpage",maxpage);
		      listM.addAttribute("listcount",totalCount);
		      listM.addAttribute("find_field",find_field);
		      listM.addAttribute("find_name",find_name);
		
		
		return "bbs/bbs_list";
	}
	
	//내용보기 + 답변폼 + 수정폼 + 삭제폼
	@RequestMapping("/bbs_cont")
	public ModelAndView bbs_cont(@RequestParam("bbs_no") int bbs_no, String state, int page, BbsVO b) {
		// @RequestParam("bbs_no")를 서블릿으로표현하면 request.getParameter("bbs_no")와 같다. int page로 표현해도 get으로 전달된
		// page 파라미터값을 받을 수 있다.
		
		if(state.equals("cont")) { //내용보기일때만 조회수 증가
			b=this.bbsService.getBbsCont(bbs_no); 
		}else { //답변폼, 수정폼, 삭제폼일때는 조회수 증가 안함
			b= this.bbsService.getBbsCont2(bbs_no);
		}
		String bbs_cont =b.getBbs_cont().replace("\n", "<br>"); //textarea 내용입력박스에서 엔터키 친부분을 줄바꿈 처리
		
		ModelAndView cm = new ModelAndView();
		cm.addObject("b",b); //키, 값 쌍으로저장
		cm.addObject("bbs_cont", bbs_cont);
		cm.addObject("page", page); //쪽번호 ->페이징에서 내가 본쪽번호로 이동하기 위해서 책갈피 기능 구현을 위한 페이지 번호 저장

		if(state.equals("cont")) {
			cm.setViewName("bbs/bbs_cont");
		}else if(state.equals("reply")) {
			cm.setViewName("bbs/bbs_reply");
		}else if (state.equals("edit")) {
			cm.setViewName("bbs/bbs_edit");
		}else if(state.equals("del")) {
			cm.setViewName("bbs/bbs_del");
		}
		return cm;
	}//bbs_cont()
	
	
	
	
	//답변 저장
	@RequestMapping(value="/bbs_reply_ok", method=RequestMethod.POST)
	public String bbs_reply_ok(BbsVO rb, int page) {
		/* BbsVO rb는 피라미터 이름과 빈클래스 변수명이 같으면 rb객체에 값이 저장되어 있다. page만 빼고, 이유는 page는 빈클래스에 동일 변수명으로
		 * 정의 안되어 있다.
		 */
		this.bbsService.replyBbs(rb);
		return "redirect:/bbs_list?page="+page;
	}//bbs_reply_ok()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
