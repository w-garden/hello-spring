<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Logout Page</h2>
	<form method="post" action="/customLogout">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<!-- post방식으로 처리되기 때문에 csrf 토큰값을 같이 지정한다. -->
		<input type="submit" value="로그아웃">
	</form>

</body>
</html>