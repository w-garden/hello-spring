<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="msg" value="${'안녕하세요' }" /> <%-- JSTL 변수 msg 생성 --%>

<c:set var="age" scope="page">
	${30}
</c:set>

<c:out value="${msg}" /> <%-- jstl로 출력 --%>
<br>
나이 : ${age} <%-- EL 로 출력--%>
<br>

<c:remove var="age" scope="page"/> 

나이 : ${age} <%-- EL 로 출력--%>

<hr>

<c:catch var="errmsg">
예외발생전
<%=10/0 %>
예외발생후
</c:catch>
<br>
<c:out value="예외메세지 : ${errmsg}"/>
