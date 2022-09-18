<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%-- JSTL 코드 태그립 추가 --%>

<h2> jsp 유즈빈 액션태그와 스크립트 요소, JSTL c:if조건문 비교</h2>

<%-- jsp 유즈빈 액션 태그 --%>
<jsp:useBean id="user" class="net.daum.vo.UserVO"/>

<jsp:setProperty name="user" property="lastName" value="홍"/>
<jsp:setProperty name="user" property="firstName" value="길동"/>

<%--JSP 스크립트 요소인 스크립트 릿과 표현식 --%>

성 : 
<%	if(user.getLastName() != null){ %>
	<%=user.getLastName() %>	
<%	} %>
<br>
이름 :
<% if(user.getFirstName() != null) { %>
	<%=user.getFirstName() %>
<% } %>
<hr>


<h3> JSTL c:if 조건문</h3>
성 : 
<c:if test="${!empty user.lastName}">
	${user.lastName}
</c:if>
<br>
이름 :
<c:if test="${!empty user.firstName}">
	${user.firstName }
</c:if>















