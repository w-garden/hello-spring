package net.daum.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccess implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		
		System.out.println("Login Success");
		List<String> roleNames = new ArrayList<String>();
		
		
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});//로그인한 사용자에 부여한 권한을 출력
		
		if(roleNames.contains("ROLE_ADMIN")) {//
			response.sendRedirect("/sample/admin");
			return;
		}
		if(roleNames.contains("ROLE_MEMBER")) {
			response.sendRedirect("/sample/member");
			return;
		}
			response.sendRedirect("/");
		}

}
