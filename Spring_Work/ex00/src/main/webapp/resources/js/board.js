/**
 * board.js
 */

function bw_check(){
	if($.trim($("#write").val())==""){
		alert("이름을 입력하세요!");
		$("#write").val("").focus();
		return false;
	}
	if($.trim($("#title").val())==""){
		alert("제목을 입력하세요!");
		$("#title").val("").focus();
		return false;
	}
	
	if($.trim($("#content").val())==""){
		alert("글내용을 입력하세요!");
		$("#content").val("").focus();
		return false;
	}
}