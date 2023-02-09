package net.daum.vo;

import lombok.Data;

@Data
public class MessageVO { //tbl_message 테이블의 컬럼명과 빈클래스 변수명을 같게 한다. 자바 저장빈 클래스
		private int mid;
	    private String targetid; 
	    private String sender;
	    private String message; 
	    private String senddage; 
	    
}
