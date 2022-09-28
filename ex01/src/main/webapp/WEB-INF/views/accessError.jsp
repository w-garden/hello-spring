<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>403 접근 금지 에러가 발생 했을 때 보여지는 뷰페이지 : Access Denied page</h1>
	<h2><c:out value="${msg }" /></h2>
</body>
</html>