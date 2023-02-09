<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿 MVC 방명록 목록 연습 </title>
</head>
<body>
<table border ="1">

<tr> 
<th colspan=4> 서블릿 MVC 방명록 목록보기 </th>
</tr>
<tr>
	<th>번호</th><th>글제목</th><th>글내용</th><th>등록날짜</th>
</tr>
<c:if test="${!empty glist }"> <%--목록이 있는 경우 실행 --%>
	<c:forEach var="g" items="${glist}">
	<tr>
		<th>${g.no }</th>
		<td style="font-weight:bold; padding-left:16px;">${g.title }</td>
		<td style="font-weight:bolder; padding-left:16px;">${g.cont}</td>
		<th>${g.regDate}</th>
	</tr>
	
	</c:forEach>
</c:if>

<c:if test="${empty glist }"> <%--목록이 없는 경우 실행 --%>
	<tr>
		<th colspan="4"> 방명록 목록이 없습니다. </th>
	</tr>
</c:if>
	<tr>
		<th colspan="4">
		<input type="button" value="글입력" onclick="location='/MVC/good';"/>
		<%--/MVC는 컨텍스트 패스 경로, /good 는 web.xml에서 설정한 매핑 주소 --%>
		</th>
	</tr>

</table>
</body>
</html>