<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="test" value="../images/golfclub.png" />

<h2>C:url JSTL태그</h2>
<h3><c:out value="${test}"/></h3>
<img src="${test}" width=200 height=170 alt="골프장"/>
