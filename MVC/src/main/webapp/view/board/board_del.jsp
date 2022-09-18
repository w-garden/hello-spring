<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿MVC 게시판 삭제폼 </title>
<script src= "./js/jquery.js"></script>
<script>
	function del_check() {
		if($.trim($('#del_pwd').val())==''){
			alert('비번을 입력하세요 !');
			$('#del_pwd').val('').focus();
			return false;
		}
	}
	
	
	
</script>
</head>
<body>
<div id="bDel_wrap">
	<h2 class="bDel_title">게시판 삭제</h2>
	<form method="post" action="board_del_ok.do" onsubmit="return del_check()">
		<input type="hidden" name="board_no" value="${bc.board_no }">
		<input type="hidden" name="page" value="${ page }">
		<table>
			<tr>
				<th> 비밀번호</th>
				<td><input type="password" name="del_pwd" id="del_pwd" size="14"></td>
			</tr>
		</table>
		
		<div id="bDel_menu">
			<input type="submit" value="삭제"> <input type="reset" value="취소"
			onclick="$('#del_pwd').focus()"><input type="button" value="목록"
			 onclick="location='board_list.do?page=${page}'">
		</div>
	
	</form>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</div>
</body>
</html>