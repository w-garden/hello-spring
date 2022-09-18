<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿 MVC 회원수정폼</title>
<script type="text/javascript" src="./js/jquery.js"></script>
<script src="./js/member.js"></script>
</head>

<body>
	<div id="mJoin_wrap">
		<h2 class="mJoin_title">회원정보 수정</h2>
		<form name="m" method="post" action="member_edit_ok.do" onsubmit="return edit_check();" >
	

			<table id="mJoin_t" border=1>
				<tr>
					<th>회원아이디</th>
					<td>${id }	</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="mem_pwd" id="mem_pwd"	size="14">
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="mem_pwd2" id="mem_pwd2"size="14">
				</tr>
				<tr>
					<th>회원이름</th>
					<td><input name="mem_name" id="mem_name" size=14 value="${m.mem_name }"></td>
				</tr>

				<tr>
					<th>우편번호</th>
					<td><input name="mem_zip" id="mem_zip" size=5 readonly value="${m.mem_zip }">-<input name="mem_zip2" id="mem_zip2" size=5 readonly value="${m.mem_zip2 }">
						<button type="button" onclick="post_check()">우편검색</button></td>
				</tr>

				<tr>
					<th>주소</th>
					<td><input name="mem_addr" id="mem_addr" size="40" readonly value="${m.mem_addr }">
					</td>
				</tr>
				<tr>
					<th>나머지 주소</th>
					<td><input name="mem_addr2" id="mem_addr2" size="28" value="${m.mem_addr2 }">
					</td>
				</tr>
				<tr>
					<th>폰번호</th>
					<td><select name="mem_phone01" id="mem_phone01">
							<c:forEach var="p" items="${phone}" >
								<option value="${p}"<c:if test="${m.mem_phone01 == p }">
								${'selected' }</c:if>>${p}</option>
							</c:forEach>
					</select>-<input name="mem_phone02" id="mem_phone02" size="4" maxlength="4" value="${m.mem_phone02 }">
					-<input name="mem_phone03" id="mem_phone03" size="4" maxlength="4" value="${m.mem_phone03 }">
					</td>
				</tr>
				
				<tr>
					<th>전자우편</th>
					<td>
						<input name="mail_id" id="mail_id" size="18" value="${m.mail_id }">@<input name="mail_domain" id="mail_domain" size="24" readonly value="${m.mail_domain}">
						<select name="mail_list" onchange="domain_list()">
							<c:forEach var="mail" items="${email}">
							<option value="${mail}"
							<c:if test="${m.mail_domain ==mail }">
							${'selected' }</c:if>>${mail}</option>
							</c:forEach>
						</select>
					</td>
				
				</tr>
				
			</table>
			<div id="mJoin_menu">
				<input type="submit" value="정보수정"> <input type="reset" value="수정취소" onclick="$('#mem_pwd').focus();"><input type="button" value="뒤로가기" onclick="history.back();">
			</div>



		</form>
	</div>
</body>
</html>