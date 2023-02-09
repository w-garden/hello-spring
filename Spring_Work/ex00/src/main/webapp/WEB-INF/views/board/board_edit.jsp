<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 글쓰기</title>
<script src="../resources/js/jquery.js"></script> <%--jquery 라이브러리 경로 --%>
<script src="../resources/js/board.js"></script> <%-- 유효성 검증 자바스크립트와 jQuery --%>
<link rel="stylesheet" href="../resources/css/board.css">
</head>

<body>
<form method="post" action="board_edit_ok?bno=${eb.bno }&page=${page}" onsubmit="return check();">
<%--bno, page는 get으로 전달되고, writer와 title 그리고 content는 post로 전달된다. --%>


<table border=1 style="margin: 0 auto;">
 
 	<tr>
 		<th colspan="2">스프링 MVC 게시판 수정폼</th>
 	</tr>
 	<tr>
 		<th>글쓴이 </th>
 		<td><input name="writer" id="writer" size="14" value="${eb.writer }"></td>
 	</tr>
 	<tr>
 		<th>글제목 </th>
 		 <td><input name="title" id="title" size="36" value="${eb.title }"></td>
 	</tr>
 	<tr>
 		<th>글내용 </th>
 		<td>
 		<textarea name="content" id="content" rows="10" cols="36"> ${eb.content }</textarea></td>
 	</tr>
 	<tr>
 		<th colspan="2">
 			<input type="submit" value="글수정">
 			<input type="reset" value="취소" onclick="$('#writer').focus();">
 			<input type="button" value="목록" onclick="location='/board/board_list?page=${page }';">
 			<%--페이징 쪽나누기에서 내가 본 쪽번호로 바로 이동하기 위한 책갈피 기능을 구현 --%>
 		</th>
 	</tr>
 </table>

</form>
</body>
</html>