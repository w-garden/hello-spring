<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="./js/jquery.js"></script>
<script>
	function login_check() {
		if ($.trim($('#login_id').val()) == '') {
			alert('로그인 아이디를 입력하세요 !');
			$('#login_id').val('').focus();
			return false;
		}
		if ($.trim($('#login_pw').val()) == '') {
			alert('로그인 비번을 입력하세요 !');
			$('#login_pw').val('').focus();
			return false;
		}

	}

	//비번 찾기
	function pwd_find() {
		$url = "pwd_find.do"//비번찾기 매핑주소
		window.open($url, "비번검색", "width=500px, height=300px, scrollbars=yes");
		//open(공지창경로, 공지창이름, 공지창 속성) 메서드
	}
</script>
</head>
<body>
	<c:if test="${empty id }">
		<%--id가 비어있다면 실행 => 로그인 전화면 --%>
		<div id="Login_wrap">
			<h2 class="Login_title">로그인폼</h2>
			<form method="post" action="member_login_ok.do"
				onsubmit="return login_check()">
				<table border=1 id="Login_t">
					<tr>
						<th>아이디</th>
						<td><input name="login_id" id="login_id" size="14"
							tabindex="1"></td>
						<%--tabindex속성값을 1로 지정하면 탭키를 눌렀을 때 첫번째포커스로 가진다. --%>

						<th rowspan="2"><input type="submit" value="로그인"></th>
					</tr>

					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="login_pw" id="login_pw"
							size="14"></td>
					</tr>

				</table>
				<div id="Login_menu">
					<input type="button" value="비번찾기" onclick="pwd_find();"> <input
						type="button" value="회원가입" onclick="location='member_join.do';">

				</div>
			</form>
		</div>
	</c:if>

	<c:if test="${!empty id }">
		<%--id가 있다면 실행 => 로그인 이후 화면 --%>
		<div id="Index_wrap">
			<h2 class="Index_title">로그인 이후 메인화면</h2>
			<form method="post" action="member_logout.do">
				<table id="Index_t">
					<tr>
						<th><input type="button" value="정보수정"
							onclick="location='member_edit.do';"> <input
							type="button" value="회원탈퇴" onclick="location='member_del.do';">
							<input type="submit" value="로그아웃"></th>
					</tr>
					<tr>
						<th>${id }님로그인을 환영합니다.</th>
					</tr>

					<c:if test="${!empty profile}">
						<%--프로필 사진이 있는 경우만 실행 --%>
						<tr>
							<th><img src="./upload${profile }" width="100" height="100"
								alt="프로필 사진"></th>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
	</c:if>
</body>
</html>