<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿 MVC 게시판 수정품</title>
<script src="./js/jquery.js"></script>
<script src="./js/board.js"></script>
</head>
<body>
   <div id="bWrite_wrap">
      <h2 class="bWrite_title">게시판 수정</h2>
      <form method="post" action="board_edit_ok.do" 
         onsubmit="return bw_check();">
         <%-- 히든값 전달 --%>
         <input type="hidden" name="board_no" value="${bc.board_no }"/> <%--번호가 전달 --%>
         <input type="hidden" name="page" value="${page }"> <%--페이징 쪽나누기에서 책갈피 기능을 구현하기 위해서 쪽번호를 전달. 책갈피 기능이란
         내가 본 페이지 번호로 바로 이동하는 것을 말한다. --%>
         <table id="bWrite_t">
            <tr>
               <th>이름</th>
               <td><input name="board_name" id="board_name" size="14" value="${bc.board_name }"/></td>
            </tr>
            <tr>
               <th>제목</th>
               <td><input name="board_title" id="board_title" size="35" value="${bc.board_title }"/></td>
            </tr>
            <tr>
               <th>비밀번호</th>
               <td><input type="password" name="board_pwd" id="board_pwd"
                  size="14" /></td>
            </tr>
            <tr>
               <th>내용</th>
               <td><textarea name="board_cont" id="board_cont" rows="9"
                     cols="36">${bc.board_cont }</textarea></td>
            </tr>
         </table>
         <div id="bWrite_menu">
            <input type="submit" value="수정" /> <input type="reset" value="취소"   onclick="$('#board_name').focus();" /> 
            <input type="button"  value="목록" onclick="location='board_list.do?page=${page}';" />
               <%-- board_list.do?page=쪽번호가 get방식으로 전달됨 => 내가 본 페이지로 바로 이동하는 책갈피 기능 --%>
         </div>
      </form>
   </div>
</body>
</html>