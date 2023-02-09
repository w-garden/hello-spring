<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8"/>


<h3> JSTL fmt 태그 예제</h3>
파라미터 name 값 : <c:out value="${param.name }"></c:out>

<hr>
<form method="post" action="JSTL_17.jsp">
	<label for="name">이름</label>
	
	<input name="name" id="name" size="14" value="${param.name}"/>
	<input type="submit" value="확인" />
</form>