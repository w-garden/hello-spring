<%@ tag body-content="empty" pageEncoding="UTF-8" %>
<%--JSP의 page 디렉티브 대신 tag 디렉티브를 사용한다. tag 디렉티브에는 tag파일 정보가 설정되어있다.-
body-content 속성은 커스텀 태그의 본문 내용이 있는지 없는지를 설정하는 부분인데 empty 이면 본문 내용이 없다는 의미이다

.tag 확장자를 뺀 태그 파일명 message가 바로 커스텀 태그명이 된다. 만약 10개의 커스텀 태그명이 있다면 태그파일명도 10개가 되어야 한다.
--%>

메시지 출력하는 태그