<%@ tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@ attribute name="name2" required="true" %>
<%-- name2라는 속성을 지정. required="true" 로 설정해서 필수 속성으로 지정함. --%>
<%@ attribute name="welcome2" required="true" %>
<%-- welcome2라는 필수 속성을 지정 --%>

<h2>${name2}님, ${welcome2}</h2>