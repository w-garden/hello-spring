<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<form action="save" method="post">

    <table border="1">
        <caption>회원가입  폼</caption>
        <tr>
            <td> username</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td> age</td>
            <td><input type="text" name="age"/></td>
        </tr>
        <tr>
        <th colspan="2">
            <button type="submit">회원가입</button>
        </th>
        </tr>
    </table>

</form>
</body>
</html>