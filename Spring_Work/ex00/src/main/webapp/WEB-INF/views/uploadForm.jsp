<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반적(전통적)인 동기식 이진파일 첨부</title>
</head>
<body>
<h2>동기식 이진파일 첨부기능인 자료실</h2>
<form method="post" action="uploadFormAction" enctype="multipart/form-data">
<%-- 파일첨부기능인 자료실을 만들때 유의사항 
	1. 파일을 첨부해서 서버에 업로드 하려면 method 방식은 post 방식으로
	2. 
 --%>
 <input type="file" name="uploadFile" multiple>
 <%--multiple 속성을 사용하면 하나의 input type="file"로 다중 첨부파일을 동시에 업로드 할 수 있다. 이 속성은 ie10이상에서만 사용가능 --%>
 <input type="submit" value="파일 업로드">
</form>
</body>
</html>