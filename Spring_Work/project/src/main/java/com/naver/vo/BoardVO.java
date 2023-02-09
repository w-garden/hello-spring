package com.naver.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardVO {
 /*
  *  네임피라미터 이름,빈클래스변수명,테이블 컬럼명을 일치시킨다.
  */
	private int board_no;
	private String board_name;
	private String board_title;
	private String board_pwd;
	private String board_cont;
	private int board_hit;
	private int board_ref;
	private int board_step;
	private int board_level;
	private String board_date;
	
	//페이징 쪽나누기 관련변수
    private int startrow;//시작행 번호
    private int endrow;//끝행 번호
    
    //검색기능
    private String find_name;//검색어
    private String find_field;//검색필드        
	
}



















