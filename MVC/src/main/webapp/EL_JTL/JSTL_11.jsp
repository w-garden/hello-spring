<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2> c:if JSTL문과 파라미터 값</h2>

<c:if test="true">
	무조건 실행 <hr>
</c:if>

<c:if test="${param.name == 'hong' }">
	name 파라미터 값이 '${param.name }' 입니다. <hr>
</c:if>

<c:if test="${param.age >= 18}">
	당신의 나이는 18세 이상입니다.
</c:if>