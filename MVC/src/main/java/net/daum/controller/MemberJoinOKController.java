package net.daum.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import net.daum.dao.MemberDAOImpl;
import net.daum.vo.MemberVO;

public class MemberJoinOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String saveFolder =request.getRealPath("upload");
		//이진파일이 업로드 되는실제 경로를 구한다
		
		int fileSize =5*1024*1024; //이진파일 업로드 최대크기를 5M
		MultipartRequest multi =null; //이진 파일 업로드
		
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		MemberVO m =new MemberVO();
		
		String mem_id =multi.getParameter("mem_id");
		String mem_pwd =multi.getParameter("mem_pwd");
		String mem_name =multi.getParameter("mem_name");
		String mem_zip =multi.getParameter("mem_zip");
		String mem_zip2 =multi.getParameter("mem_zip2");
		String mem_addr =multi.getParameter("mem_addr");
		String mem_addr2 =multi.getParameter("mem_addr2");
		String mem_phone01 =multi.getParameter("mem_phone01");
		String mem_phone02 =multi.getParameter("mem_phone02");
		String mem_phone03 =multi.getParameter("mem_phone03");
		String mail_id =multi.getParameter("mail_id");
		String mail_domain =multi.getParameter("mail_domain");
		
		File UpFile = multi.getFile("mem_file"); //첨부한 이진파일을 가져옴
		
		if(UpFile != null) { //첨부한 파일이 있다면
			String fileName = UpFile.getName(); //첨부
			Calendar c= Calendar.getInstance();
			int year =c.get(Calendar.YEAR); //년도
			int month = c.get(Calendar.MONTH)+1; //월, +1을 하는 이유는 1월이 0으로 반환되기 때문
			int date = c.get(Calendar.DATE); //일
			
			String homedir=saveFolder+"/"+year+"-"+month+"-"+date; //오늘 날짜 폴더 경로저장
			File path01 = new File(homedir);
			if(!(path01.exists())) { //오늘날짜 폴더 경로가 없다면
				path01.mkdir(); //오늘날짜 폴더 생성
			}
			Random r = new Random();
			int random = r.nextInt(100000000);//0이상 1억미만 사이의 정수 숫자 난수를 발생
			
			int index=fileName.lastIndexOf("."); //첨부파일에서 마침표 위치를 구함
			String fileExtendsion = fileName.substring(index+1); //마침표 이후부터 마지막 문자까지 구함. 즉 파일의 확장자를 구함
			
			String refileName ="member" + year +month+date+random+"."+fileExtendsion;
			//새로운 첨부파일명
			
			String fileDBName = "/" +year+"-"+month+"-"+date+"/"+refileName;//오라클에 저장될 레코드값
			UpFile.renameTo(new File(homedir+"/"+refileName)); 
			//새롭게 생성된 폴더 경로에 변경된 파일로 실제 업로드
			
			m.setMem_file(fileDBName);
		}
		m.setMem_id(mem_id); m.setMem_pwd(mem_pwd); m.setMem_name(mem_name); m.setMem_zip(mem_zip);
		m.setMem_zip2(mem_zip2); m.setMem_addr(mem_addr); m.setMem_addr2(mem_addr2); m.setMem_phone01(mem_phone01);
		m.setMem_phone02(mem_phone02); m.setMem_phone03(mem_phone03); m.setMail_id(mail_id); m.setMail_domain(mail_domain);
		
		MemberDAOImpl mdao = new MemberDAOImpl();
		int re =mdao.insertMember(m); //회원저장
		
		if(re==1) {
			ActionForward forward = new ActionForward();
			forward.setRedirect(true); //새로운 매핑주소로 이동
			forward.setPath("member_login.do");
			return forward;

		}
		
		
		return null;

		
	}

}
