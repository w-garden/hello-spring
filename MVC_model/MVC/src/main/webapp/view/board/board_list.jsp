<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿 mvc 게시판 목록(검색기능과 페이징 쪽나누기)</title>
</head>
<body>
	<div id=bList_wrap">
		<form method="get" action="board_list.do">
			<%--검색 기능 폼 --%>
			<h2 class="bList_title">게시판 목록</h2>
			<div class="bList_count">글개수: ${listcount }</div>
			<table border=1>
				<tr>
					<th width="6%" height="26">번호</th>
					<th width="50%">제목</th>
					<th width="14%">글쓴이</th>
					<th width="17%">작성일</th>
					<th width="14%">조회수</th>
				</tr>

				<c:if test="${!empty blist }">
					<%--검색전후 목록이 있는경우 실행 --%>
					<c:forEach var="b" items="${blist }">
						<tr>
							<td align="center"><c:if test="${b.board_step==0 }">
									<%--원본글일 때만 글그룹 번호를출력 --%>
						${b.board_ref }
					</c:if> &nbsp;</td>

							<td>
							<c:if test="${b.board_step != 0 }"> <%--답변글일때만 실행되고 들여쓰기와 답변글 이미지가 나옴 --%>
								<c:forEach begin="1" end="${b.board_step }" step="1">
								&nbsp;
								</c:forEach>
								<img src="./images/AnswerLine.gif"/> <%--답변글 이미지 --%>
							</c:if>
							<a href="board_cont.do?board_no=${b.board_no }&page=${page}&state=cont">${b.board_title }</a>
							<%-- *.do?board_no=번호&page=쪽번호&state=cont --%>
							</td>
							<td align="center"> ${b.board_name }</td>
							<td align="center"> ${b.board_date }</td>
							<td align="center"> ${b.board_hit }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty blist }">
					<tr>
						<th colspan="5">게시물 목록이 없습니다. </th>
					</tr>
				</c:if>
				
				
			</table>
			
			<%--검색전후 페이징(쪽나누기) --%>
			<div id="bList_paging">
				<%--검색 전 페이징 --%>
				<c:if test="${(empty find_field) && (empty find_name) }"> <%--검색 필드와 검색어가 없을떄 --%>
					<c:if test="${page <= 1 }">
						[이전]&nbsp;
					</c:if>
					<c:if test="${page>1 }">
						<a href="board_list.do?page=${page-1 }">[이전]</a>&nbsp;
					</c:if>
					
					<%--현재 쪽번호 출력 --%>
					<c:forEach var="a" begin="${startpage }" end="${endpage }" step="1">
						<c:if test="${a==page }"> <%--현재 쪽번호가 선택된 경우 --%>
							<${a }>
						</c:if>
						<c:if test="${a!=page }"> <%-- 현재 페이지가 선택 안된 경우 --%>
							<a href="board_list.do?page=${a }">[${a}]</a>
						</c:if>
					</c:forEach>
						<c:if test="${page >= maxpage }">
							[다음]
						</c:if> 
						<c:if test="${page < maxpage }">
							<a href="board_list.do?page=${page+1 }">[다음]</a>
						</c:if> 
				</c:if>
				
				<%--검색이후 페이징 (쪽나누기) --%>
				<c:if test="${(!empty find_field) || (!empty find_name) }">
					 <c:if test="${page<=1}"> [이전]&nbsp;    </c:if>
     					<c:if test="${page>1}">
      						<a href="board_list.do?page=${page-1}&find_field=${find_field}&find_name=${find_name}">[이전]</a>&nbsp;
     						</c:if>
     						<%--get으로 find_field와 find_name을 전달해야 검색이후 페이징 목록을 유지한다. 검색필드와 검색어를
     						전달하지 않으면 검색전 전체 페이징 목록으로 이동해서 검색효과가 사라진다 --%>
     						
     						
     						
     						<%--현재 쪽번호 출력 --%>
     							<c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
      								<c:if test="${a == page}"> <%-- 현재 페이지가 선택된 경우 --%>
       									<${a}>
      								</c:if>
      							<c:if test="${a != page}"> <%--현재페이지가 선택 안된 경우 --%>
      							<a href="board_list.do?page=${a}&find_field=${find_field}&find_name=${find_name}">[${a}]</a>&nbsp;
     							 </c:if>      
    							 </c:forEach>
     
    					 <c:if test="${page >= maxpage}">
     											  [다음]
    						  </c:if>
    						  <c:if test="${page < maxpage}">
     						 <a href="board_list.do?page=${page+1}">[다음]</a>
     						 </c:if>
				</c:if>
			</div>
			<div id="bListW_menu">
					<input type="button" value="글쓰기" onclick="location='board_write.do?page=${page}';">
					<%--?page=페이지 번호를 get으로 전달해야 책갈피 기능이 페이징에서 구현된다. --%>
					<c:if test="${(!empty find_field) && (!empty find_name) }"> <%-- 검색이후 --%>
						<input type="button" value="전체목록" onclick="location='board_list.do?page=${page}';">
 					</c:if>
			</div>
			
			<%-- 검색폼 추가 --%>
			<div>
				<table>
					<tr>
						<th>
							<select name="find_field">
								<option value="board_title" <c:if test="${find_field =='board_title' }"> ${'selected' }</c:if>>제목</option>
								<option value="board_cont" <c:if test="${find_field =='board_cont' }"> ${'selected' }</c:if>>내용</option>
							</select>
							
							<input type="search" name="find_name" value="${find_name }" size="16">
							<input type="submit" value="검색">
						</th>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
