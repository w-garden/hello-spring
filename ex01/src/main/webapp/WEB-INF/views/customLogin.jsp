<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정의 로그인 페이지</title>
</head>
<body>
	<h1>Custom Login Page</h1>
	<h2><c:out value="${error }"/></h2>
	<h2><c:out value="${logout }"/></h2>
	
	<form method="post" action="/login">
		<div>
			<input type="text" name="username" value="admin"/>
		</div>
		
		<div>
			<input type="password" name="password" value="admin"/>
		</div>
		
		
		<div>
			<input type="submit" value="Login"/>
		</div>
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<%--
			CSRF(Cross-site request Forgery)의 특징>>
			1. 스프링 시큐리티에서 post방식을 이용하는 경우 기본적으로 CSRF 토큰을 이용한다.
			2. CSRF 토큰이 사용되는데 '사이트간 위조 방지' 를 목적으로 특정한 값의 토큰을 사용햐는 방식이다.
			3. CSRF 토큰은 사용자가 임의로 변하는 특정한 토큰값을 서버에서 체크하는 방식이다.
			4. 서버에서는 브라우저에서 데이터를 전송할 때 CSRF 토큰을 함께 전송한다. 사용자가 POST방식으로 특정한 작업(로그인 작업) 을 할때는 브라우저가
			전송한 CSRF 토근값과 서버가 보관하고 있는 토큰값을 서로 비교한다. 만일 토큰 값이 다르다면 작업을 처리하지 않는 방식이다.
			5. 서버에서 생성하는 토큰은 일반적으로 난수를 생성해서 해커가 난수를 찾을 수 없도록 한다.  
		
		 --%>
	</form>
</body>
</html>