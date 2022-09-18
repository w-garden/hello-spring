<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*, javax.naming.*, javax.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DBCP 커넥션 풀로 오라클 연결 </title>
</head>
<body>
<%!

Connection con = null; 	//DB연결
DataSource ds= null; //커넥션 풀 관리

%>
<%
	//스크립트 릿 => 자바 문법을 따라간다.
	
	try{
		Context ctx= new InitialContext();
		ds=(DataSource)ctx.lookup("java:comp/env/jdbc/xe"); //JNDI 디렉토리 서비스로 server.xml에 설정된 오라클 DB명 xe를 찾아서 DBCP 커넥션풀 관리 ds생성
		con =ds.getConnection(); //커넥션 풀 관리 ds로 db연결 con생성
		out.print("DBCP 연결 성공~");
	}catch(Exception e){e.printStackTrace();}
		finally{
			try{
				if(con !=null) con.close();
			}catch(Exception e){e.printStackTrace();
				
			}
		}
	
	
%>
</body>
</html>