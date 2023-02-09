<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>c:choose 다중 조건문(선택문)</h2>
<ul>
 <c:choose>
  <c:when test="${param.name == 'hong'}">
    <li>당신의 이름은 <strong>${param.name}</strong> 입니다.</li>
  </c:when>
  <c:otherwise>
    <li>해당 사항 없습니다.</li>
  </c:otherwise>
 </c:choose>
</ul>