<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c2" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8" />
<%-- method=post 방식으로 전달된  한글자료를 안 깨지게 한다. --%>

<c2:set var="welcome" value="환영합니다." />

<form method="post"> <%-- action속성을 설정하지 않으면 자기 자신파일에 데이터가 보내진다. --%>
  이름 : <input name="name" size="14" />
 <input type="submit" value="전송" />
</form>

<hr/>
<c2:if test="${fn:length(param.name) > 0}">
<%-- 이름을 입력한 경우 실행 --%>
 <tagFile:attribute welcome2="${welcome}" name2="${param.name}"></tagFile:attribute>
 <%--attribute 커스텀 태그명이 .tag를 뺀 태그 파일명이 된다.tagFile이 5번 줄에 설정된 prefix속성값
 접두어가 된다. --%>
</c2:if>