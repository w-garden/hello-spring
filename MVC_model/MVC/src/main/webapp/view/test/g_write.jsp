<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿 MVC 방명록 글쓰기 </title>
<script src="./js/jquery.js"> </script>
<script>
	function check(){
		if($.trim($('#title').val())==''){
			alert('글제목을 입력하세요');
			$('#title').val('').focus();
			return false;
		}
		if($.trim($('#cont').val())==''){
			alert('글내용을 입력하세요');
			$('#cont').val('').focus();
			return false;
		}
			
	}
</script>
</head>
<body>
<form method="post" action="/MVC/g_ok"  onsubmit="return check();">
<%--/MVC는 컨텍스트 패스 경로, /g_ok는 주소창에서 실행되는 주소값인 url 매핑 주소 --%>
<h2>서블릿 MVC 방명록 글쓰기 연습</h2>
글제목 : <input name="title" id="title" size="36" /> <br><br>
글내용 : <textarea name="cont" id="cont" rows=10 cols=38></textarea>
<hr>
<input type="submit" value="전송">
<input type="reset" value="취소">
</form>
</body>
</html>