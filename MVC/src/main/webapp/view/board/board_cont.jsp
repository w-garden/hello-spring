<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿 MVC 게시판 내용보기 </title>
</head>
<body>
	<div id ="bCont_wrap">
		<h2 class="bCont_title">게시판 내용보기</h2>
		<table id="bCont_t" border=1>
			<tr>
				<th>글제목</th><td>${bc.board_title }</td>
			</tr>
			<tr>
				<th>글내용</th><td>${board_cont }</td>
			</tr>
			<tr>
				<th>조회수</th><td>${bc.board_hit }</td>
			</tr>
		</table>
		<div id="bCont_menu">
			<input type="button" value="답변" onclick="location='board_cont.do?board_no=${bc.board_no}&page=${page }&state=reply';">
			<input type="button" value="수정" onclick="location='board_cont.do?board_no=${bc.board_no}&page=${page }&state=edit';">
			<input type="button" value="삭제" onclick="location='board_cont.do?board_no=${bc.board_no}&page=${page }&state=del';">
			<input type="button" value="목록" onclick="location='board_list.do?page=${page}';">
		</div>
	</div>
</body>
</html>