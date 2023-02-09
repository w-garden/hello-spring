<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아작스 댓글</title>
<style>
#modDiv{ /*댓글 수정화면 영역 */
	width:300px; height:100px;
	background-color: #C5BF96;
	border-radius: 3px;
	position: absolute;
	top:50%; left: 50%;
	margin-top: -50px; margin-left: -150px;
	padding: 10px;
	z-index: 1000; /*position 속성값이 absolute or fixed인 곳에서 사용가능하다. 이속성은 요소가 겹쳐지는 순서를 제어할 수 있다.*/
}

</style>
</head>
<body>
	<%--댓글 수정화면 --%>
	<div id="modDiv" style="display: none;"> <%--일단 화면에 안 나오게 한다. --%>
		<div class="modal-title"></div>
		
		<div>
		<textarea rows="3" cols="36" id="replytext"></textarea>
		</div>
		<div>
			<button type="button" id="replyModBtn">댓글수정</button>
			<button type="button" id="replyDelBtn">댓글삭제</button>
			<button type="button" id="closeBtn" onclick="modDivClose();">닫기</button>
		</div>
	</div>
	
	
	<h2>아작스 댓글 연습</h2>
	<div>
		<div>
			댓글 작성자 : <input name="replyer" id="newReplyWriter">
		</div>
		<br>
		<div>
			댓글 작성자 : <textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
		</div>
		<br>
		
		<button id="replyAddBtn">댓글등록</button>
	</div>
	
	<br>
	<hr>
	<br>
	
	<%-- 댓글목록--%>
	
	
	<ul id="replies"></ul>
	<script src="../resources/js/jquery.js"></script> <%-- jQuery 라이브러리 --%>
	<script type="text/javascript">
	
	var bno=7; //스프링 MVC 게시판 번호값
	
	getAllList()
	function getAllList(){
		$.getJSON("/replies/all/"+bno, function(data){ //json 데이터를 get방식으로 처리, 비동기식으로 가져온 데이터는 data매개변수에 저장
		var result="";
		
		$(data).each(function(){ //each()함수로반복
			result += "<li data-rno='"+this.rno+"' class='replyLi'>"+this.rno+": <span class='con' style='color:blue; font-weight:bold;'>"+
			this.replytext + "</span>" + "<button>댓글수정</button></li><br>"
		});
		$('#replies').html(result); //해당영역에 html() 함수로 문자와 태그를 함께 변경 적용.
		});
	}//댓글 목록함수
	
	
	
	//댓글 추가 
	$('#replyAddBtn').on('click', function(){
		$replyer = $('#newReplyWriter').val(); //댓글작성자
		$replytext = $('#newReplyText').val(); //댓글내용
		
		$.ajax({
			type:'post',
			url:'/replies', //URL 매핑주소
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override" : "POST"
				
			},
			datatype:'test',
			data:JSON.stringify({
				bno:bno, //게시판 번호
				replyer:$replyer, //댓글 작성자
				replytext:$replytext //댓글내용
			}),
			success:function(result){//비동기식으로 받아오는 것이 성공시 호출. 받아온 데이터는 result매개변수에 저장
				if(result=='SUCCESS'){
					alert('댓글이 등록되었습니다!');
					getAllList(); //댓글목록함수 호출
				}
			}
		}); //jQuery 비동기식 아작스 함수 => 가장 많이 사용
	});	
		
	
	
	//댓글 수정 화면
	$('#replies').on("click", ".replyLi button", function(){
		var reply = $(this).parent(); //parent()부모 요소를 선택 => li태그를 가리킴
		//this는 버튼
		var rno = reply.attr("data-rno"); //data-rno속성값을 구함 => 댓글 번호
		var replytext = reply.children('.com').text(); //댓글내용
		
		$('.modal-title').html(rno); //댓글번호를 표시
		$('#replytext').val(replytext); //댓글 내용을 표시
		$('#modDiv').show('slow'); //댓글 수정화면을 나오게 한다
	});
	
	
	//댓글 수정화면 닫기
	function modDivClose(){
		$('#modDiv').hide('slow');
	}
	
	
	
	//댓글 수정완료
	$('#replyModBtn').on('click', function(){
		$rno = $('.modal-title').html();//댓글번호
		$replytext = $('#replytext').val();//수정할 댓글내용
		
		$.ajax({
			type:'put', //메서드 방식
			url:'/replies/'+$rno, //ReplyController.java에 등록된 매핑주소
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"PUT"
				
					
			},
			data:JSON.stringify({
				replytext:$replytext
			}),
			dataType:'text', //받아오는 자료형
			success:function(result){
				if(result== 'SUCCESS'){
					alert('댓글 수정완료!');
					$('#modDiv').hide('slow'); //댓글 수정화면 닫기
					getAllList();
				}
			}
		}); //아작스 함수
	});
	
	
	
	//댓글 삭제 완료
	$('#replyDelBtn').on("click", function(){
		$rno = $('.modal-title').html(); //댓글번호
		
		$.ajax({
			type:'delete',
			url:'/replies/'+$rno,
			headers: {
				"Content-Type":"application/json",
				"X-HTTP-Method-Override":"DELETE"
			},
			dataType:'text',
			success:function(result){
				if(result=='SUCCESS'){
					alert("댓글이 삭제되었습니다!");
					$('#modDiv').hide('slow');
					getAllList();
				}
			}
		});
	});
	
	
	
	</script>
	
	
	
	
	
</body>
</html>