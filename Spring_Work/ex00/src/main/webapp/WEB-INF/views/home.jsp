<%@ page contentType="text/html; charset=UTF-8" %>
<%-- contentType속성값은 웹브라우저에 출력되는 문자와 태그,언어코딩 타입을 설정한다.=>이 부분이 정확해야 html태그,자바스크립트 ,한글이 안깨지고
출력 잘된다. --%>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello Spring!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<%-- ${serverTime} 표현언어 EL에서 serverTime은 HomeController 스프링 컨트롤러 클래스에서 
model.addAttribute("serverTime", formattedDate );에 의해서 저장된 serverTime키이름을 참조해서 날짜/시간값을 가져오는 것이다. 이것 대
신에 서블릿 api인 request.setAttribute()를 사용해도 된다. --%>
</body>
</html>
