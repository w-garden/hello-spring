<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2> 통화형과 날짜/시간형태 JSTL</h2>

<c:set var ="price" value="10000"/>
통화형 : <fmt:formatNumber value="${price}" type="currency" currencySymbol="원"/>

<hr>

<c:set var="now" value="<%=new java.util.Date() %>"/>
<fmt:formatDate value="${now }" type="date" dateStyle="full" />
<%-- fmt:formatDate는 날짜정보를 담고 있다. date날짜 스타일을 풀형태로 표현 --%>

<hr>

<fmt:formatDate value="${now }" type="time"/>
<%--time 형태는 시간값 --%>