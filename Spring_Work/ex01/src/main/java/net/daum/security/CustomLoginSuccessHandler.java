package net.daum.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		
       System.out.println("Login Success");
       
       List<String> roleNames = new ArrayList<>();
       
       auth.getAuthorities().forEach(authority -> {
    	   roleNames.add(authority.getAuthority());
       });//로그인 한 사용자에 부여한 권한을 체크해서 가져와 컬렉션에 추가
       
       System.out.println("ROLE NAMES : " + roleNames);//사용자 권한을 출력
       
       if(roleNames.contains("ROLE_ADMIN")) {//관리자 권한일 때
    	   response.sendRedirect("/sample/admin");
    	   return;
       }
       
       if(roleNames.contains("ROLE_MEMBER")) {//일반 회원일때
    	   response.sendRedirect("/sample/member");
    	   return;
       }
       
       response.sendRedirect("/");       
	}
}
























