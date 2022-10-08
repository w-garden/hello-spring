package com.naver.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.AdminBbsService;
import com.naver.vo.BbsVO;
import com.oreilly.servlet.MultipartRequest;

@Controller
public class AdminBbsController {

	@Autowired
	private AdminBbsService adminBbsService;

	//관리자 자료실 목록
	@GetMapping("admin_bbs_list")
	public String admin_bbs_list(Model listM, BbsVO b, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String admin_id = (String) session.getAttribute("admin_id");

		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요')");
			out.println("location='admin_index';");
			out.println("</script>");
		}else {
			int page=1;//쪽번호
			int limit=7;//한페이지에 보여지는 목록개수
			if(request.getParameter("page") != null) {
				page=Integer.parseInt(request.getParameter("page"));         
			}
			String find_name=request.getParameter("find_name");//검색어
			String find_field=request.getParameter("find_field");//검색필드
			b.setFind_field(find_field);
			b.setFind_name("%"+find_name+"%");
			//%는 오라클 와일드 카드 문자로서 하나이상의 임의의 문자와 매핑 대응
			//하나의 모르는 문자와 매핑 대응하는 와일드 카드문자는 _

			int listcount=this.adminBbsService.getListCount(b);
			//전체 레코드 개수 또는 검색전후 레코드 개수
			//System.out.println("총 게시물수:"+listcount+"개");

			b.setStartrow((page-1)*7+1);//시작행번호
			b.setEndrow(b.getStartrow()+limit-1);//끝행번호

			List<BbsVO> blist=
					this.adminBbsService.getBbsList(b);	//검색전후 목록

			//총페이지수
			int maxpage=(int)((double)listcount/limit+0.95);
			//현재 페이지에 보여질 시작페이지 수(1,11,21)
			int startpage=(((int)((double)page/10+0.9))-1)*10+1;
			//현재 페이지에 보여줄 마지막 페이지 수(10,20,30)
			int endpage=maxpage;
			if(endpage > startpage+10-1) endpage=startpage+10-1;

			listM.addAttribute("blist",blist);
			//blist 키이름에 값 저장
			listM.addAttribute("page",page);
			listM.addAttribute("startpage",startpage);
			listM.addAttribute("endpage",endpage);
			listM.addAttribute("maxpage",maxpage);
			listM.addAttribute("listcount",listcount);   
			listM.addAttribute("find_field",find_field);
			listM.addAttribute("find_name", find_name);

			return "admin/admin_bbs_list";	//뷰페이지 폴더경로와 파일명 지정      
		}
		return null;
	}//admin_bbs_list()
	
	//관리자 자료실 글쓰기
	@RequestMapping("admin_bbs_write")
	public ModelAndView admin_bbs_write(HttpServletResponse response, HttpSession session, int page) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out= response.getWriter();
		String admin_id = (String) session.getAttribute("admin_id");

		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요')");
			out.println("location='admin_index';");
			out.println("</script>");
		}else {
			ModelAndView wm = new ModelAndView("admin/admin_bbs_write");
			wm.addObject("page",page); //페이징에서 책갈피 기능때문에 page에 쪽번호 저장
			return wm;
		}
		return null;
	}
	
	//관리자 자료실 저장
	@PostMapping("admin_bbs_write_ok")
	public String admin_bbs_write_ok(HttpServletResponse response, HttpServletRequest request, HttpSession session, BbsVO b) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out= response.getWriter();
		String admin_id = (String) session.getAttribute("admin_id");

		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('관리자로 다시 로그인 하세요')");
			out.println("location='admin_index';");
			out.println("</script>");
		}else {
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
			
			adminBbsService.insertBbs(b); //자료실 저장
			
			return "redirect:/bbs_list"; //자료실 목록보기로 이동
			
			
			
			
			
			
			
			
			
		}
		
		return null;
	}//admin_bbs_write_ok()
}
