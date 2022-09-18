<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<h2> JSTL sql 태그립</h2>
<sql:setDataSource var="con" driver="oracle.jdbc.OracleDriver" url="jdbc:oracle:thin:@127.0.0.1:1521:xe" user="week" password="week" />



<sql:update dataSource="${con}">
	insert into test values(1,'홍길동')
</sql:update>

<sql:update dataSource="${con}">
	insert into test values(2,'신호철')
</sql:update>

<sql:query var="rs" dataSource="${con}">
	select * from test
</sql:query>

<c:forEach items="${rs.rows}" var = "data">
	${data['num'] }
	${data['name'] } <hr>
</c:forEach>