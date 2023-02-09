package net.daum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daum.dao.MemberDAOImpl;
import net.daum.vo.ZipCodeVO;
import net.daum.vo.ZipCodeVO2;

public class ZipFindOKController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8"); //포스트로 전달된 한글자료를 안깨지게 한다.
		String dong = request.getParameter("dong").trim(); //우편 검색한 동을 받아옴, 양쪽 공백제거
		MemberDAOImpl mdao = new MemberDAOImpl();
		ActionForward forward = new ActionForward();
		
		List<ZipCodeVO> zlist=mdao.zipFind("%"+dong+"%"); //%는 하나이상의 임의의 모르는문자와 매핑 대응하는 검색에서 사용하는 와일드 카드 문자이다. 검색된 주소목록을 가져온다
		
		List<ZipCodeVO2> zlist2 = new ArrayList<>();
		
		for(ZipCodeVO z: zlist) {
			ZipCodeVO2 z2=new ZipCodeVO2();
			z2.setZipcode(z.getZipcode());
			z2.setAddr(z.getSido()+ " "+z.getGugun()+" "+z.getDong()+" "+z.getBunji());
			//시도 구군 동 (ex:서울시 강남구 역삼동 23번지)
			
			zlist2.add(z2);
		}
		request.setAttribute("zipcodelist", zlist2); //zipcodelist 속성키이름 컬렉션 zlist2를 저장
		request.setAttribute("dong", dong); //입력한 동을 저장
		
		forward.setRedirect(false);  //false로 줘야 키값을 유지하고 기존 매핑주소도 유지
		forward.setPath("./view/member/zip_find.jsp");
		return forward;
	}

}
