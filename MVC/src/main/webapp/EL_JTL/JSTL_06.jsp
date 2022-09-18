<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String phone="010-999-9999";
	request.setAttribute("phone", phone);
%>

<h2>구분자를 기준으로 문자 분리 => c:forTokens jstl문</h2>

<c:forTokens var="p" items="${phone}" delims="-">
	<%-- -구분자를 기준으로 폰번호를 분리 --%>
	<c:out value="${p}"/> <hr>
</c:forTokens>