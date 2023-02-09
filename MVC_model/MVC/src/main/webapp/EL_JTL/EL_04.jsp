<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> </title>
</head>
<body>
<h3>폼태그내에서 입력한 값을 EL %{param.name}으로 받는 실습</h3>
	<form method="post" action="EL_04.jsp">
		<label for="name">이름</label>
		<%--label for 이름과 input 태그 id속성명이 같으면 label for 이름만 선택해도 입력박스 선택효과가 난다 --%>
		<h1>이름을 입력하세요</h1>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>		
		<input name="name" id="name" size="14" value="${param.name }"/>
		<input type="submit" value="확인">
		<hr>
		<h4>입력하신 이름은 ${param.name } 입니다</h4>
	</form>
</body>
</html>