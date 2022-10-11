package com.naver.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@Controller //@Controller 애노테이션을 설정하면 스프링에서 컨트롤러로 인식한다.
public class BbsController { /* 자료실 스프링 컨트롤러 클래스 */

	@Autowired
	private BbsService bbsService;
	
	
	//자료실 글입력 폼
	@GetMapping("/bbs_write") //get방식으로 접근하는 매핑주소를 처리. bbs_write 매핑주소 등록
	public ModelAndView bbs_write(HttpServletRequest request) {
		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		ModelAndView wm=new ModelAndView("bbs/bbs_write");//생성자 인자값으로 뷰페이지(뷰리졸브) 경로 설정:/WEB-INF/views/bbs/
		//bbs_write.jsp
		wm.addObject("page",page);//키,값 쌍으로 저장
		return wm;
	}//bbs_write()
	
	
	//자료실 저장
	@PostMapping("/bbs_write_ok")
	public String bbs_write_ok(BbsVO b,HttpServletRequest request) throws Exception{
		String saveFolder=request.getRealPath("/resources/upload");//이진 파일 업로드 서버 경로=>톰켓 WAS서버에 의해서 변경된 실제 경로:
		//C:\20220607\Spring_Work\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\project\resources\
		//upload
		
		int fileSize=5*1024*1024; //이진파일 업로드 최대크기
		MultipartRequest multi=null; //이진파일을 가져올 참조변수
		
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");	
		
		String bbs_name=multi.getParameter("bbs_name");
		String bbs_title=multi.getParameter("bbs_title");
		String bbs_pwd=multi.getParameter("bbs_pwd");
		String bbs_cont=multi.getParameter("bbs_cont");

		File upFile = multi.getFile("bbs_file");//첨부한 이진파일을 가져온다.
		
		if(upFile != null) {//첨부한 이진파일이 있는 경우 실행
			String fileName=upFile.getName();//첨부한 파일명
			Calendar cal=Calendar.getInstance();//칼렌더는 추상클래스로 new로 객체 생성을 못함. 년월일 시분초 값을 반환
			int year=cal.get(Calendar.YEAR);//년도값
			int month=cal.get(Calendar.MONTH)+1;//월값, +1을 한 이유는 1월이 0으로 반환 되기 때문에
			int date=cal.get(Calendar.DATE);//일값
			
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date;//오늘 날짜 폴더 경로 저장
			File path01=new File(homedir);
			
			if(!(path01.exists())){
				path01.mkdir();//오늘날짜 폴더 생성
			}
			Random r=new Random();//난수를 발생시키는 클래스
			int random=r.nextInt(100000000);//0이상 1억 미만의 정수 숫자 난수 발생
			
			/*첨부 파일 확장자를 구함*/
			int index=fileName.lastIndexOf(".");//마침표를 맨 오른쪽부터 찾아서 가장 먼저 나오는 .의 위치번호를 맨 왼쪽부터 카운터 해서 반환
			//첫문자는 0부터 시작
			String fileExtendsion=fileName.substring(index+1);//마침표 이후부터 마지막 문자까지 구함.즉 첨부파일 확장자를 구함.
			String refileName="bbs"+year+month+date+random+"."+fileExtendsion;//새로운 파일명 저장
			String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;//데이터베이스에 저장될 레코드값
			upFile.renameTo(new File(homedir+"/"+refileName));//생성된 폴더에 변경된 파일명으로 실제 업로드
			
			b.setBbs_file(fileDBName);
		}else {//첨부파일이 없는 경우
			String fileDBName="";
			b.setBbs_file(fileDBName);
		}
		b.setBbs_name(bbs_name); b.setBbs_title(bbs_title); b.setBbs_pwd(bbs_pwd); b.setBbs_cont(bbs_cont);
		
		this.bbsService.insertBbs(b);//자료실 저장
		
		return "redirect:/bbs_list";//자료실 목록보기 매핑주소로 이동
	}//bbs_write_ok()
	
	
	//자료실 목록
	@RequestMapping(value="/bbs_list",method=RequestMethod.GET) //get으로 접근하는 매핑주소를 처리
	public String bbs_list(Model listM,HttpServletRequest request,@ModelAttribute BbsVO b) {
		  int page=1;
	      int limit=10;//한페이지에 보여지는 목록개수
	      if(request.getParameter("page") != null) {
	       page=Integer.parseInt(request.getParameter("page"));//페이지번호를 정수숫자로 변경해서 저장         
	      }
	       String find_name=request.getParameter("find_name");//검색어
	       String find_field=request.getParameter("find_field");//검색 필드
	       b.setFind_field(find_field);
	       b.setFind_name("%"+find_name+"%");
	        //SQL문에서 %는 검색에서 하나이상의 모르는 임의의 문자와 매핑 대응하는
	        //와일드 카드문자
	        
	      int totalCount=this.bbsService.getRowCount(b);
	      //검색전 총레코드 개수,검색후 레코드 개수
	      
	       b.setStartrow((page-1)*10+1);//시작행번호
	       b.setEndrow(b.getStartrow()+limit-1);//끝행 번호
	      
	      List<BbsVO> blist=this.bbsService.getBbsList(b); //검색 전후 목록
	      
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
		  return "bbs/bbs_list";//뷰페이지 경로 : /WEB-INF/views/bbs/bbs_list.jsp
	}//bbs_list()
	
	//내용보기+답변폼+수정폼+삭제폼
	@RequestMapping("/bbs_cont")
	public ModelAndView bbs_cont(@RequestParam("bbs_no") int bbs_no, String state, int page,BbsVO b) {
	//@RequestParam("bbs_no")를 서블릿으로 표현하면 request.getParameter("bbs_no")와 같다. int page로 표현해도 get으로 전달된
	//page 피라미터값을 받을 수 있다.	
		
	   if(state.equals("cont")) {//내용보기 일때만 조회수 증가
		   b=this.bbsService.getBbsCont(bbs_no);
	   }else {//답변폼,수정폼,삭제폼일때는 조회수 증가 안함.
		   b=this.bbsService.getBbsCont2(bbs_no);
	   }
	   
	   String bbs_cont=b.getBbs_cont().replace("\n","<br/>");//textarea 내용입력박스에서 엔터키 친부분을 줄 바꿈 처리
	   
	   ModelAndView cm=new ModelAndView();
	   cm.addObject("b",b);//키, 값 쌍으로 저장
	   cm.addObject("bbs_cont",bbs_cont);
	   cm.addObject("page",page);//페이징에서 내가 본 쪽번호로 이동하기 위해서 책갈피 기능 구현을 위한 페이지 번호 저장
	   
	   if(state.equals("cont")) {//내용보기일때
		   cm.setViewName("bbs/bbs_cont");//뷰리졸브 경로(뷰페이지 경로): /WEB-INF/views/bbs/bbs_cont.jsp
	   }else if(state.equals("reply")) {//답변폼
		   cm.setViewName("bbs/bbs_reply");		  
	   }else if(state.equals("edit")) {//수정폼
		   cm.setViewName("bbs/bbs_edit");
	   }else if(state.equals("del")) {//삭제폼
		   cm.setViewName("bbs/bbs_del");
	   }
	   return cm;
	}//bbs_cont()
	
	//답변저장
	@RequestMapping(value="/bbs_reply_ok",method=RequestMethod.POST)
	public String bbs_reply_ok(BbsVO rb,int page) {
		/* BbsVO rb는 피라미터 이름과 빈클래스 변수명이 같으면 rb객체에 값이 저장되어 있다. page만 빼고, 이유는 page는 빈클래스에 동일 변수명으로
		 * 정의 안되어 있다.
		 */
		this.bbsService.replyBbs(rb);//답변저장
		return "redirect:/bbs_list?page="+page;
	}//bbs_reply_ok()
	
	
	//자료실 수정
	@PostMapping("bbs_edit_ok")
	public ModelAndView bbs_edit_ok(HttpServletRequest request,HttpServletResponse response,BbsVO eb)
	throws Exception{
		response.setContentType("text/html;charset=UTF-8");//브라우저에 출력되는 문자와 태그 언어 코딩 타입을 설정
		PrintWriter out=response.getWriter();
		String saveFolder=request.getRealPath("/resources/upload");//이진파일 업로드 실제 경로를 구함.
		int fileSize=5*1024*1024;//이진파일 업로드 최대 크기
		
		MultipartRequest multi=null;//첨부한 파일을 받을 참조변수
		multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
		
		int bbs_no=Integer.parseInt(multi.getParameter("bbs_no"));
		int page=1;
		if(multi.getParameter("page") != null) {
			page=Integer.parseInt(multi.getParameter("page"));
		}
		String bbs_name=multi.getParameter("bbs_name");
		String bbs_title=multi.getParameter("bbs_title");
		String bbs_pwd=multi.getParameter("bbs_pwd");
		String bbs_cont=multi.getParameter("bbs_cont");
		
		BbsVO db_pwd=this.bbsService.getBbsCont2(bbs_no);//DB로 부터 비번을 가져옴
		
        if(!db_pwd.getBbs_pwd().equals(bbs_pwd)) {
        	out.println("<script>");
        	out.println("alert('비번이 다릅니다!');");
        	out.println("history.go(-1);");
        	out.println("</script>");
        }else {
        	File upFile=multi.getFile("bbs_file");//첨부한 파일을 가져온다.
        	if(upFile != null) {//첨부한 파일이 있는 경우
        		String fileName=upFile.getName();//첨부한 파일명을 구함
        		File delFile=new File(saveFolder+db_pwd.getBbs_file());//삭제할 파일 객체 생성
        		if(delFile.exists()) {//삭제할 파일이 존재하면
        			delFile.delete();//기존 첨부파일 삭제
        		}
        		Calendar cal=Calendar.getInstance();
        		int year=cal.get(Calendar.YEAR);
        		int month=cal.get(Calendar.MONTH)+1;
        		int date=cal.get(Calendar.DATE);
        		
        		String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
        		File path01=new File(homedir);
        		if(!(path01.exists())) { path01.mkdir();}
        		Random r=new Random();
        		int random=r.nextInt(100000000);
        		
        		/*첨부파일 확장자 구함*/
        		int index=fileName.lastIndexOf(".");
        		String fileExtendsion=fileName.substring(index+1);//.이후부터 마지막 문자 즉 첨부파일 확장자를 구함.
        		String refileName="bbs"+year+month+date+random+"."+fileExtendsion;
        		String fileDBName="/"+year+"-"+month+"-"+date+"/"+refileName;
        		upFile.renameTo(new File(homedir+"/"+refileName));
        		eb.setBbs_file(fileDBName);
        	}else {//수정 첨부 파일을 하지 않았을 때
        		String fileDBName="";
        		if(db_pwd.getBbs_file() != null) {
        			eb.setBbs_file(db_pwd.getBbs_file());
        		}else {
        		    eb.setBbs_file(fileDBName);	
        		}
        	}//수정 첨부파일을 첨부한 경우와 안한 경우 분기문(조건문)
        	eb.setBbs_no(bbs_no); eb.setBbs_name(bbs_name); eb.setBbs_title(bbs_title); eb.setBbs_cont(bbs_cont);
        	
        	this.bbsService.editBbs(eb);//번호를 기준으로 글쓴이,글제목,글내용,첨부파일 수정
        	
        	ModelAndView em=new ModelAndView("redirect:/bbs_cont");
        	em.addObject("bbs_no",bbs_no);
        	em.addObject("page",page);
        	em.addObject("state","cont");
        	return em;//주소창에 다음과 같이 실행된다. bbs_cont?bbs_no=번호&page=쪽번호&state=cont 즉 주소창에 노출되는 get방식으로
        	//3개의 인자값이 전달된다.
        }
		return null;
	}//bbs_edit_ok()
	
	
	//자료실 삭제
	@RequestMapping("/bbs_del_ok") //get or post 양쪽 방식 모두 실행됨.
	public String bbs_del_ok(int bbs_no,int page,String del_pwd,HttpServletResponse response,
	HttpServletRequest	request) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String up=request.getRealPath("/resources/upload");
		
		BbsVO db_pwd=this.bbsService.getBbsCont2(bbs_no);
		if(!db_pwd.getBbs_pwd().equals(del_pwd)){
			out.println("<script>");
			out.println("alert('비번이 다릅니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else{
			this.bbsService.delBbs(bbs_no);//번호를 기준으로 자료실 삭제
			
		    if(db_pwd.getBbs_file() != null) {//첨부파일이 있는 경우
		    	File delFile=new File(up+db_pwd.getBbs_file());//삭제할 파일 객체 생성
		    	delFile.delete();//폴더는 삭제 안되고, 폴더 안의 파일만 삭제
		    }
		    return "redirect:/bbs_list?page="+page;
		}
		return null;
	}//bbs_del_ok()
}

















