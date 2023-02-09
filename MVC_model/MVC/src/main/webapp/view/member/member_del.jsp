<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 </title>
<script src="./js/jquery.js"></script>
<script>
	function del_check(){
		if($.trim($('#del_pwd').val())==''){
			alert('비번을 입력하세요!');
			$('#del_pwd').val('').focus();
			return false;
		}
		if($.trim($('#del_cont').val())==''){
			alert('탈퇴 사유를 입력하세요!');
			$('#del_cont').val('').focus();
			return false;
		}
	}
</script>
</head>
<body>
	<div id ="mDel_wrap">
		<h2 class="mDel_title">회원탈퇴</h2>
		<form action="member_del_ok.do" method="post" onsubmit="return del_check()">
			<table id ="mDel_t" border="1">
				<tr>
					<th>회원아이디 </th> <td>${id }</td>
				</tr>
				<tr>
					<th>회원이름 </th> <td>${dm.mem_name }</td>
				</tr>
				<tr>
					<th>비밀번호 </th> <td><input type="password" id="del_pwd" name="del_pwd" size="14"></td>
				</tr>
				<tr>
					<th>탈퇴사유</th><td><textarea name="del_cont" id="del_cont" rows="9" cols="36"></textarea></td>
				</tr>
			</table>
			<div id="mDel_menu">
				<input type="submit" value="탈퇴">
				<input type="reset" value="취소" onclick="$('#del_pwd').focus()">
 			</div>
		</form>
	</div>
</body>
</html>