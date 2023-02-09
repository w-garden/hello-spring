<%@ page contentType="text/html; charset=UTF-8"%>
<%
request.setAttribute("name","홍길동");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> </title>
</head>
<body>
<h1> 표현언어 내장객체 접근자와 기존 JSP 표현법</h1>
<h3>표현언어 닷(.) 연산자 접근</h3>
${requestScope.name} <%--requestScope는 jsp 내장객체의 request에 해당한다. name 속성 키이름값을 가져온다 --%>
${param.id } <hr> <%-- 파라미터네임 id에 실려온 값을 가져와서 출력. *.jsp?id=값 형태의 주소창에 노출되는 get방식으로 값을 전달해야 한다. --%>


<h3>브라켓 연산자([]) 접근</h3>
${requestScope["name"] } <%--name 속성 키이름 값을 가져온다 --%>
${param["id"] } <hr> <%--파라미터 id에 실려온 값을 출력 --%>

<h4> 기존 jsp 로 표현</h4>
<%=request.getAttribute("name") %>   <%--name 속성 키 이름 값을 표현 --%> 
<%= request.getParameter("id") %> <hr>  <%-- 파라미터 이른 id 값을 가져옴 --%>
</body>
</html>