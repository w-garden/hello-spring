<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="score" value="<%=new int[] { 90, 100, 80, 85, 95, 75 }%>" />

<h2> c:forEach JSTL 반복문 </h2>
**점수 출력 ** <br>
<c:forEach var="s" items="${score}" begin="0" end="5">
	${s}점 &nbsp; &nbsp;
	
	<c:set var="sum" value="${sum+s}" /> 
	
 </c:forEach>
 <hr>
 	<h1>총합 : <c:out value= "${sum}"/> </h1>
 	<hr>
 <%--문제) 1 3 5 7 9 홀수값이 출력되는 forEach 반복문 작성 --%>
 
<c:set var="number" value ="<%=new int[] {1,2,3,4,5,6,7,8,9,10} %>"/>

** 홀수만 출력 ** <br><br>
<c:forEach var="n" begin="1" end="10" step="2">
	${n}점 &nbsp; &nbsp;
</c:forEach>
