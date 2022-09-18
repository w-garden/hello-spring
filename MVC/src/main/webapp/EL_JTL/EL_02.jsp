<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> </title>
</head>
<body>
	<h3>기존 유즈빈 액션태그와 표현언어 인 El의 비교 </h3>
	
	<jsp:useBean id="user" class="net.daum.vo.UserVO">
		<jsp:setProperty  name="user" property="lastName" value="홍"/>
		<jsp:setProperty  name="user" property="firstName" value="길동"/>
		
		성 : <jsp:getProperty name="user" property="lastName"/> <br>
		이름 : <%=user.getFirstName()%>
	</jsp:useBean>
	
	<hr>
	
	<%--간단히 표현언어로 출력 --%>
	${user.lastName }
	${user.firstName }	
		
		
</body>
</html>