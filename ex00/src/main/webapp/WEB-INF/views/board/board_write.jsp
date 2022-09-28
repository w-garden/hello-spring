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
<form method="post" onsubmit="return check();">
<%--action 속성을 생략하면 이전 매핑주소가 액션매핑주소가 된다. 즉 board_write가 된다. 매핑주소 구분은 get or post 로 처리한다. --%>
 <table border=1 style="margin: 0 auto;">
 
 	<tr>
 		<th colspan="2">스프링 MVC 게시판 입력폼 </th>
 	</tr>
 	<tr>
 		<th>글쓴이 </th>
 		<td><input name="writer" id="writer" size="14"></td>
 	</tr>
 	<tr>
 		<th>글제목 </th>
 		 <td><input name="title" id="title" size="36"></td>
 	</tr>
 	<tr>
 		<th>글내용 </th>
 		<td>
 		<textarea name="content" id="content" rows="10" cols="36"></textarea></td>
 	</tr>
 	<tr>
 		<th colspan="2">
 			<input type="submit" value="등록">
 			<input type="reset" value="취소" onclick="$('#writer').focus();">
 			<input type="button" value="목록" onclick="location='/board/board_list?page=${page }';">
 			<%--페이징 쪽나누기에서 내가 본 쪽번호로 바로 이동하기 위한 책갈피 기능을 구현 --%>
 		</th>
 	</tr>
 </table>

</form>
</body>
</html>