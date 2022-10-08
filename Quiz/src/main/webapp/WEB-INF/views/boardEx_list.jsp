<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<table border=1 style="margin:0 auto; border-collapse: collapse;">
	<caption>게시판 목록</caption>
	<caption>총 게시물개수 : ${count }</caption>
	
	<tr>
	<th>글번호</th>
	<th>글쓴이</th>
	<th>제목</th>
	<th>조회수</th>
	<th>등록날짜</th>
	</tr>
	
	<c:forEach var="e" items="${tlist }">
	<tr>
		<td>${e.b_no}</td>
		<td>${e.b_writer}</td>
		<td>${e.b_title}</td>
		<td>${e.b_hit}</td>
		<td>${e.regdate}</td>
	</tr>
	</c:forEach>
	
	
	<tr>
		<th colspan="5"> 
			<c:if test="${page<=1 }"> [이전]&nbsp; </c:if>
			<c:if test="${page >1 }"> <a href="/boardEx_list?page=${page-1 }">[이전]</a>&nbsp;</c:if>
			<c:forEach var="p" begin="${startpage }" end="${endpage }" step="1">
				<c:if test="${p==page }"> <${p}> </c:if>
				<c:if test="${p!=page }"> <a href="/boardEx_list?page=${p }">[${ p}]</a>&nbsp;</c:if>
			</c:forEach>
			<c:if test="${page >=maxpage}"> [다음]</c:if>
			<c:if test="${page <maxpage }"><a href="boardEx_list?page=${page+1 }">[다음]</a></c:if>
		
		
		</th>
		</tr>
	</table>
</body>
</html>