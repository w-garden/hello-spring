<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿 MVC 회원가입폼</title>
<script type="text/javascript" src="./js/jquery.js"></script>
<script src="./js/member.js"></script>
</head>

<body>
	<div id="mJoin_wrap">
		<h2 class="mJoin_title">회원가입</h2>
		<form name="m" method="post" action="member_join_ok.do"
			onsubmit="return join_check();" enctype="multipart/form-data">
			<%--폼태그 내에서 enctype="mutipart/form-data 속성값을 저장하면 post로 전달된 이진파일
		(binary mode file) 을 처리할 수 있다. 특히 파일첨부한 자료실 기능을 만드려면 반드시 이 속성값을 지정해야 한다.
		폼 태그에서 method속성을 생략하면 기본값이 get이고, get인 경우는 자료실 기능을 만들 수 있다. 자료실 기능을 만드려면 라이브러리가 필요하다.
		이 라이브러리는 WEB-INF/lib 폴더에 넣어줘야한다. --%>

			<table id="mJoin_t" border=1>
				<tr>
					<th>회원아이디</th>
					<td><input name="mem_id" id="mem_id" size="14"> <input
						type="button" value="ID중복검색" onclick="id_check();"> <br>
						<span id=idcheck></span> <%--jQuery 아작스 비동기식 프로그램으로 가져온 데이터가 출력되는 부분이다. --%>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="mem_pwd" id="mem_pwd"
						size="14">
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="mem_pwd2" id="mem_pwd2"
						size="14">
				</tr>
				<tr>
					<th>회원이름</th>
					<td><input name="mem_name" id="mem_name" size=14></td>
				</tr>

				<tr>
					<th>우편번호</th>
					<td><input name="mem_zip" id="mem_zip" size=5 readonly>-<input
						name="mem_zip2" id="mem_zip2" size=5 readonly>
						<button type="button" onclick="post_check()">우편검색</button></td>
				</tr>

				<tr>
					<th>주소</th>
					<td><input name="mem_addr" id="mem_addr" size="40" readonly>
					</td>
				</tr>
				<tr>
					<th>나머지 주소</th>
					<td><input name="mem_addr2" id="mem_addr2" size="28">
					</td>
				</tr>
				<tr>
					<th>폰번호</th>
					<td><select name="mem_phone01" id="mem_phone01">
							<c:forEach var="phone" items="${phone}" >
								<option value="${phone}">${phone}</option>
							</c:forEach>
					</select>-<input name="mem_phone02" id="mem_phone02" size="4" maxlength="4">
					-<input name="mem_phone03" id="mem_phone03" size="4" maxlength="4">
					</td>
				</tr>
				
				<tr>
					<th>전자우편</th>
					<td>
						<input name="mail_id" id="mail_id" size="18">@<input name="mail_domain" id="mail_domain" size="24" readonly>
						<select name="mail_list" onchange="domain_list()">
							<c:forEach var="mail" items="${email}">
							<option value="${mail}">${mail}</option>
							
							</c:forEach>
						</select>
					</td>
				
				</tr>
				
				<tr>
					<th>프로필 사진 첨부 </th>
					<td> <input type="file" name="mem_file"></td>
				</tr>
				
			</table>
			<div id="mJoin_menu">
				<input type="submit" value="회원가입"> <input type="reset" value="가입취소" onclick="$('#mem_id').focus();">
			</div>



		</form>
	</div>
</body>
</html>