<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="fruit" value="${param.name}" />
<%-- *.jsp?name=값 형태의 get방식으로 name 파라미터 이름에 값을 전달하면 ${param.name} 으로 받아서
JSTL 변수 fruit에 저장 --%>

<h2>JSTL c:if 조건문과 c:choose 다중조건문</h2>
파라미터 name에 실려온 값 :
<c:if test="${!empty fruit }">
<c:out value="${fruit }"/>
</c:if>

<hr>

<c:choose>
	<c:when test="${fruit =='apple' }">
		<c:out value="${fruit}"/> : 사과
	</c:when>
	<c:when test="${fruit =='banana' }">
		<c:out value="${fruit}"/> : 바나나
	</c:when>
	<c:when test="${fruit =='orange' }">
		<c:out value="${fruit}"/> : 오렌지
	</c:when>
	<c:when test="${fruit =='pear' }">
		<c:out value="${fruit}"/> : 배
	</c:when>
	<c:otherwise>해당사항없음</c:otherwise>
</c:choose>