<%@ page contentType="text/html; charset=UTF-8"%>


<h3>EL(Expression Language : 표현언어 -> 외부 라이브러리 필요없음)</h3>
[1]정수형 : ${200}
<br>
[2]실수형 숫자 : ${10.3}
<br>
[3]문자열 : ${"홍길동"}
<br>
[4]논리형 : ${true}
<br>
[5]null : ${null}
<hr>


<h3>표현언어 연산자</h3>
<%
String input = null;
%>
\${10+5} = ${10+5}
<br>
\${5/2} = ${5/2}
<br>
<%-- 

\${5 div 2} = ${5 div 2} <br >
\${5 mod 2} = ${5 div 2} <br>
 오류아님
--%>
\${5 gt 2} = ${5 gt 2}
<br>
\${empty input} = ${empty input}
<%-- input 변수가 비어있다면 참 --%>