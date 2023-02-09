package com.naver.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.service.MemberService;
import com.naver.vo.MemberVO;
import com.naver.vo.ZipcodeVO;
import com.naver.vo.ZipcodeVO2;

import pwdconv.PwdChange;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	//회원관리 로그인 폼 페이지
	@GetMapping("member_login")
	public ModelAndView member_Login() {
		return new ModelAndView("member/member_Login");//로그인 폼 뷰페이지 설정: /WEB-INF/views/member/member_Login.jsp			
	}//member_Login()


	//사용자 회원가입폼
	@RequestMapping("member_join")
	public String member_join(Model m) {
		String[] phone= {"010","011","019"};
		String[] email= {"naver.com","daum.net","nate.com","google.com","직접입력"};
		m.addAttribute("phone",phone);
		m.addAttribute("email",email);
		return "member/member_join";//뷰리졸브 경로 설정(뷰페이지 경로 설정)
	}//member_join()

	//아이디 중복 검색
	@PostMapping("/member_idcheck")
	public String member_idcheck(String id,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();

		MemberVO db_id=this.memberService.idCheck(id);//아이디에 해당 회원정보를 DB로 부터 검색
		int re=-1;//중복 아이디가 없을때 반환값

		if(db_id != null) {//중복 아이디가 있는 경우
			re=1;
		}
		out.println(re);//값 반환
		return null;
	}//member_idcheck()

	//우편주소 검색 공지창
	@RequestMapping(value="/zip_find",method=RequestMethod.GET) //GET방식일때 실행
	public ModelAndView zip_find() {
		ModelAndView zm=new ModelAndView();
		zm.setViewName("member/zip_find");
		return zm;
	}//zip_find()

	//우편검색 결과
	@RequestMapping(value="/zip_find_ok",method=RequestMethod.POST) //POST방식일때 실행
	public ModelAndView zip_find_ok(String dong) {
		List<ZipcodeVO> zlist=this.memberService.zipFind("%"+dong+"%");//%는 검색할때 사용하는 와일드 카드 SQL 연산자로 하나이상의
		//임의의 모르는 문자와 매핑 대응

		List<ZipcodeVO2> zlist2=new ArrayList<>();

		for(ZipcodeVO z:zlist) {
			ZipcodeVO2 z2=new ZipcodeVO2();

			z2.setZipcode(z.getZipcode());//우편번호 저장
			z2.setAddr(z.getSido()+" "+z.getGugun()+" "+z.getDong());//시도 구군 동을 저장

			zlist2.add(z2);//컬렉션에 추가
		}
		ModelAndView zm=new ModelAndView("member/zip_find");//생성자 인자값으로 뷰페이지 경로 설정
		zm.addObject("zipcodelist",zlist2);
		zm.addObject("dong",dong);
		return zm;
	}//zip_find_ok()

	//회원저장
	@RequestMapping("/member_join_ok")
	public String member_join_ok(MemberVO m) {
		//뷰페이지의 네임 피라미터 이름과 빈클래스의 변수명이 같으면 MemberVO m의 m에 저장할 회원정보가 담겨져 있다
		m.setMem_pwd(PwdChange.getPassWordToXEMD5String(m.getMem_pwd()));//비번을 암호화
		this.memberService.insertMember(m);//회원저장

		return "redirect:/member_login";
	}//member_join_ok()

	//비번찾기 공지창
	@RequestMapping("/pwd_find") //get or post방식일때 실행.
	public String pwd_find() {
		return "member/pwd_find";
	}//pwd_find()

	//비번 찾기 결과
	@PostMapping("/pwd_find_ok") //post로 접근하는 url매핑주소 처리
	public String pwd_find_ok(@RequestParam("pwd_id") String pwd_id, String pwd_name,HttpServletResponse response,
			MemberVO m,Model fm) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();

		m.setMem_id(pwd_id); m.setMem_name(pwd_name);
		MemberVO pm=this.memberService.pwdMember(m);//아이디와 회원이름을 기준으로 DB로 부터 회원정보 검색

		if(pm == null) {
			out.println("<script>");
			out.println("alert('회원으로 검색되지 않습니다.\\n 올바른 회원정보를 입력하세요!');");
			out.println("history.go(-1);");
			out.println("</script>");
		}else {
			Random r=new Random();
			int pwd_random=r.nextInt(100000);//0이상 십만미만 사이의 정수 숫자 난수를 발생
			String ran_pwd=Integer.toString(pwd_random);//임시 정수 비번을 문자열로 변경
			m.setMem_pwd(PwdChange.getPassWordToXEMD5String(ran_pwd));//임시 비번 암호화
			this.memberService.updatePwd(m);//암호화 된 임시 비번 수정

			fm.addAttribute("ran_pwd",ran_pwd);//임시비번을 ran_pwd키이름에 저장
			return "member/pwd_find_ok";//뷰페이지 경로(뷰리졸브 경로) :  /WEB-INF/views/pwd_find_ok.jsp
		}
		return null;
	}//pwd_find_ok()

	//로그인 인증처리
	@RequestMapping("/member_login_ok")
	public ModelAndView member_login_ok(String login_id,String login_pwd,HttpServletResponse response,
			HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();

		MemberVO m=this.memberService.loginCheck(login_id);//로그인 인증처리

		if(m == null) {
			out.println("<script>");
			out.println("alert('가입 안된 회원입니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			if(!m.getMem_pwd().equals(PwdChange.getPassWordToXEMD5String(login_pwd))) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!');");
				out.println("history.go(-1);");
				out.println("</script>");
			}else{
				session.setAttribute("id",login_id);//세션 id키이름에 아이디 저장
				return new ModelAndView("redirect:/index");//로그인 인증 후 메인화면으로 이동
			}//if else
		}
		return null;
	}//member_login_ok()

	//로그인 인증후 메인화면
	@GetMapping("/index")
	public String index(HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");

		if(isLogin(session, response)) {//==true가 생략 : 로그인 된 상태
			return "member/member_Login";
		}
		return null;
	}//index()


	//로그아웃
	@PostMapping("member_logout")
	public String member_logout(HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();

		session.invalidate();//세션 만료 => 로그아웃

		out.println("<script>");
		out.println("alert('로그아웃 되었습니다!');");
		out.println("location='member_login';");
		out.println("</script>");

		return null;
	}//member_logout()


	//정보수정폼
	@RequestMapping("member_edit")
	public ModelAndView member_edit(HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();

		String id=(String)session.getAttribute("id");
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login';");
			out.println("</script>"); 
		}else{		 
			String[] phone= {"010","011","019"};
			String[] email= {"naver.com","daum.net","nate.com","google.com","직접입력"};

			MemberVO em=this.memberService.getMember(id);//아이디에 해당하는 회원정보를 DB로 부터 가져옴.

			ModelAndView m=new ModelAndView();
			m.addObject("m",em);//m 키이름에 em객체 저장
			m.addObject("phone",phone);
			m.addObject("email",email);
			m.setViewName("member/member_Edit");
			return m;
		}
		return null;
	}//member_edit()

	//정보수정 완료
	@RequestMapping("/member_edit_ok")
	public String member_edit_ok(MemberVO m,
			HttpServletResponse response,
			HttpServletRequest request)
					throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login';");
			out.println("</script>");
		}else {
			m.setMem_id(id);
			m.setMem_pwd(PwdChange.getPassWordToXEMD5String(
					m.getMem_pwd()));//정식비번 암호화
			this.memberService.updateMember(m);//정보수정
			/* 문제 풀이)
			 * 1. 아이디를 기준으로 비번,이름,우편번호,주소,폰번호,
			 * 전자멜주소를 수정되게 한다.
			 */
			out.println("<script>");
			out.println("alert('정보 수정했습니다!');");
			out.println("location='member_edit';");
			out.println("</script>");
		}
		return null;
	}//member_edit_ok()

	//회원탈퇴 폼
	@RequestMapping("/member_del")
	public ModelAndView member_del(
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login';");
			out.println("</script>");
		}else {
			MemberVO m=this.memberService.getMember(id);
			ModelAndView dm=
					new ModelAndView("member/member_del");
			dm.addObject("m",m);
			return dm;
		}
		return null;
	}//member_del()

	/*회원탈퇴 완료*/
	@RequestMapping("/member_del_ok")
	public String member_del_ok(
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam("del_pwd") String del_pwd,
			@RequestParam("del_cont") String del_cont)
					throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");

		if(id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login';");
			out.println("</script>");
		}else {
			del_pwd=PwdChange.getPassWordToXEMD5String(del_pwd);
			//비번을 암호화
			MemberVO db_pwd=this.memberService.getMember(id);
			if(!db_pwd.getMem_pwd().equals(del_pwd)) {
				out.println("<script>");
				out.println("alert('비번이 다릅니다!');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				MemberVO dm=new MemberVO();
				dm.setMem_id(id); dm.setMem_delcont(del_cont);
				this.memberService.delMem(dm);//회원탈퇴
				
				session.invalidate();//세션만료

				out.println("<script>");
				out.println("alert('회원 탈퇴 했습니다!');");
				out.println("location='member_login';");
				out.println("</script>");
			}
		}
		return null;
	}//member_del_ok()	
	
	//반복적인  로그인을 하나로 줄이기
	public static boolean isLogin(HttpSession session,HttpServletResponse response)
			throws Exception{
		PrintWriter out=response.getWriter();
		String id=(String)session.getAttribute("id");

		if(id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='member_login';");
			out.println("</script>");

			return false;
		}
		return true;//로그인 된 경우는 true를 반환
	}//isLogin()
}


















