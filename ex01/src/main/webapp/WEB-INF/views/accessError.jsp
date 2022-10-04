<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSTL 코어 태그립 추가 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> </title>
</head>
<body>
 <H1>403 접근 금지 에러가 발생 했을때 보여지는 뷰페이지 : Access Denied Page</H1>
 <h2><c:out value="${msg}" /></h2>
</body>
</html>