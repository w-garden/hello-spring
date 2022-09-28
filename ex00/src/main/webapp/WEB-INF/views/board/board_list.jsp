<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="../resources/css/board.css">

</head>
<body>
	<table border="1">
		<tr>
			<td colspan="5" align="right">총 게시물 수: ${totalCount} 개</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>등록날짜</th>
		</tr>
		<c:if test="${!empty list}">
			<c:forEach var="b" items="${list}">
				<tr>
					<th>${b.bno}</th>
					<th><a href="/board/board_cont?bno=${b.bno}&page=${page}">
							${b.title}<c:if test="${b.replycnt != 0}">
&nbsp;&nbsp;<strong> [ 댓글개수 :${b.replycnt} 개]</strong>
</c:if>
					</a>
					<%-- 매핑주소?bno=번호값&page=쪽번호 &기호로
구분해서 2개의 피라미터이름게 각 번호와 쪽번호를 담아서
전달한다.--%></th>
					<th>${b.writer}</th>
					<th>${b.viewcnt}</th>
					<th>${b.regdate}</th>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty list}">
			<tr>
				<th colspan="5">목록이 없습니다!</th>
		</c:if>
		<%--페이징 쪽번호 출력부분 --%>
		<tr>
			<th colspan="5">
				<%-- begin --%> <c:if test="${page <= 1}">
   [이전]&nbsp;
  </c:if> <c:if test="${page > 1}">
					<a href="/board/board_list?page=${page-1}">[이전]</a>&nbsp;
  </c:if> <%--쪽번호 출력 --%> <c:forEach var="a" begin="${startpage}"
					end="${endpage}" step="1">
					<c:if test="${a == page}">
						<%--현재 쪽번호가 선택된 경우 --%>
     <${a}>
    </c:if>
					<c:if test="${a != page}">
						<%--현재 쪽번호가 선택 안된 경우 --%>
						<a href="/board/board_list?page=${a}">[${a}]</a>&nbsp;
    </c:if>
				</c:forEach> <c:if test="${page >= maxpage}">
   [다음]
  </c:if> <c:if test="${page < maxpage}">
					<a href="/board/board_list?page=${page+1}">[다음]</a>
				</c:if> <%-- end --%>
			</th>
		</tr>
		<tr>
			<td colspan="5" align="right"><input type="button" value="글쓰기"
				onclick="location='/board/board_write?page=${page}';" /> <%--location객체로 이동하는 get방식 --%>
			</td>
		</tr>
	</table>
	<script>
		var msg = '${msg}';//자바스크립트에서 스프링 컨트롤에서 저
		//장한 키이름을 참조해 EL OR JSTL로 가져올수 있다.여기서
		//는 표현언어 즉 EL로 가져옴.
		if (msg == 'SUCCESS') {
			alert('처리가 완료 되었습니다!');
		}
	</script>
</body>
</html>