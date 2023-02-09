<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비번찾기 결과 </title>
</head>
<body>
<div id="pOK_wrap">
	<h2 class="pOK_title">비번검색결과</h2>
		<table id="pOK_t">
			<tr>
				<th>비번검색결과</th>
				<td>${find_pwd }</td>
			</tr>
		
		</table>
		<div id="pOK_menu">
			<input type="button" value="닫기" onclick="self.close();">
			<%--자바 스크립트에서 self.close(); 는 자기 자신 공지창을 닫는다. --%>
		</div>
</div>
</body>
</html>