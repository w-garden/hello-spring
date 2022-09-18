<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿 MVC 게시판 답변폼</title>
<script src="./js/jquery.js"></script>
<script src="./js/board.js"></script>
</head>
<body>
   <div id="bWrite_wrap">
      <h2 class="bWrite_title">게시판 답변</h2>
      <form method="post" action="board_reply_ok.do"
         onsubmit="return bw_check();">
         <%--답변 히든값 --%>
         <input type="hidden" name="board_ref" value="${bc.board_ref }">
         <%--원본글과 답변글을 묶어주는 그룹번호 --%>
         <input type="hidden" name="board_step" value="${bc.board_step }">
         <%--원본글이면 0, 첫번째 답변글이면 1, 두번째 답변글이면 2  --%>
         <input type="hidden" name="board_level" value="${bc.board_level }">
         <%--답변글 정렬순서 --%>
         
         <input type="hidden" name="page" value="${page }">
         <%--페이징 쪽 나누기에서 책갈피 기능때문에 페이지번호를 히든으로 전달함 --%>
         
         
         <table id="bWrite_t">
            <tr>
               <th>이름</th>
               <td><input name="board_name" id="board_name" size="14" /></td>
            </tr>
            <tr>
               <th>제목</th>
               <td><input name="board_title" id="board_title" size="35" value="Re:${bc.board_title }"/></td>
            </tr>
            <tr>
               <th>비밀번호</th>
               <td><input type="password" name="board_pwd" id="board_pwd"
                  size="14" /></td>
            </tr>
            <tr>
               <th>내용</th>
               <td><textarea name="board_cont" id="board_cont" rows="9"
                     cols="36"></textarea></td>
            </tr>
         </table>
         <div id="bWrite_menu">
            <input type="submit" value="답변" /> 
            <input type="reset" value="취소"   onclick="$('#board_name').focus();" /> 
            <input type="button"  value="목록" onclick="location='board_list.do?page=${page}';" />
               <%-- board_list.do?page=쪽번호가 get방식으로 전달됨 => 내가 본 페이지로 바로 이동하는 책갈피 기능 --%>
         </div>
      </form>
   </div>
</body>
</html>