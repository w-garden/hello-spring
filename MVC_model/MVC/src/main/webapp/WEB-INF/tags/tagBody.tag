<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%-- scriptless body-content 속성값은 커스텀 태그 본문 내용이 있고, 표현 언어와 액션 태그의 처리결과를 사용할 수 있다는 의미이다. --%>
<%@ tag import="java.util.*" %>

<% Date now = new Date(); %>
오늘 날짜 : <%=now %>
<hr>
<jsp:doBody></jsp:doBody>