<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3> JSTL 코어 태그 예제 -> c:if  조건문, c:choose 다중 선택문</h3>

<c:set var="nara" value="${'korea'}"/>

<c:if test="${nara != null}">
	국가명 : <c:out value="${nara}"/> <hr>

</c:if>
<c:if test="${!empty nara}">
	국가명 : ${nara} <hr>
</c:if>

<c:choose>
	<c:when test="${nara=='korea'}">
		<c:out value="${nara }"/> 의 겨울은춥다
	</c:when>
	<c:when test="${nara=='canada'}">
		<c:out value="${canada }"/> 의 겨울은춥다
	</c:when>
	
	<c:otherwise>알수없음</c:otherwise>
</c:choose>