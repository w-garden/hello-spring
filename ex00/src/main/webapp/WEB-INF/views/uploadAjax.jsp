<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이진파일 업로드 비동기식 아작스 폼</title>
<script src="./resources/js/jquery.js"></script>
<script>
	$(document).ready(function(){
		$('#uploadBtn').on('click', function(e){
			var formData = new FormData();
			// 비동기식 아작스를 이용해서 첨부파일 처리는 Formdata 객체를 이용한다. 
			var inputFile=$("input[name='uploadFile']"); //file 객체를 구함
			var files=inputFile[0].files; //첫번째 파일 객체에서 첨부한 파일을 배열로 구한다
			
			//첨부파일을 formData에 추가
			for(var i=0; i<files.length;i++){
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url: 'uploadAjaxAction', //서버 매핑 주소
				processData: false, //processData 데이터를 컨텐츠 타입에 맞게 변환 여부
				contentType:false, //요청 컨텐트 타입
				data:formData, //아작스를 통해서 formData자체를 전송
				type:'POST',
   				success:function(data){
   						alert('성공');
			            //받아오는 것이 성공시 호출되는 콜백함
			         }

			});//jQuery ajax
		});
	});
</script>
</head>
<body>
	<h1>UpLoad with Ajax</h1>
	<input type="file" name="uploadFile" multiple>
	<hr>
	<button type="button" id="uploadBtn">Upload</button>








</body>
</html>