<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>

<%
	HashMap<String, Object> hm = new HashMap<>();
	hm.put("today",new Date());
	hm.put("name","홍길동");
	
	request.setAttribute("hm", hm);
%>

<h3>키, 값 쌍으로 출력 </h3>

<c:set var="map" value="${hm}"/>
<c:forEach var="i" items="${map }" >
	${i.key } : ${i.value } <br>
</c:forEach>

<hr>


<c:forEach var="a" items="${hm }">
	${a.key } = ${a.value } <br>
</c:forEach>


<hr>

<h3>1부터 100까지 홀수들의 누적합</h3>
<%-- 문제 ) c:set 문과 c:forEach 반복문을 사용해서 1부터 100까지 중에서 홀수들의 누적합을 구해보자 --%>
	<c:set var="sum" value="0"/>

<c:forEach var="n" begin="1" end="100" step="2">
		<c:set var="sum" value="${sum+n}"/>	
</c:forEach>

홀수 누적합  : ${sum} <hr>


<h3> 4단 구구단</h3>

<ul>
	<c:forEach var="k" begin="1" end="9">
		<li> 4*${k} = ${4*k} </li>
	</c:forEach>

</ul>

