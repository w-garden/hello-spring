<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>
<%-- 태그립 접두어로 tagFile로 함. tagdir 속성값에는 태그 파일 위치가 들어간다.
폴더명만 기술해도 tags폴더 하위의 모든 태그파일(*.tag)을 사용할 수 있다. --%>

<h3> 태그파일로 만든 내용없는 사용자 정의 태그 (커스텀 태그)</h3>

<tagFile:message /> <%-- message 태그명이 /WEB-INF/tags 폴더에 있는 태그 파일 중 message.tag 파일 이 실행됨. message태그명이 곧 .tag확장자를 뺀 태그 파일명이 된다. --%>