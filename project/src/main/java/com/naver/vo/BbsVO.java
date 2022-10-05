package com.naver.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BbsVO { //중간 자료 저장빈 클래스 역할. 자료실 저장빈 클래스

	
		private int bbs_no; //자료실 번호값
	    private String bbs_name; //글쓴이
	    private String bbs_title; //글제목
	    private String bbs_pwd; //비밀번호
	    private String bbs_cont; //글 내용
	    private String bbs_file; //첨부한 파일명
	    private int bbs_hit; //조회수
	    private int bbs_ref; //글 그룹 번호 : 원본글과 답변글을 묶어주는 역할
	    private int bbs_step; //첫번째 답변글이면1; 두번째 답변글이면 2; 원본글이면 0 => 몇번째 답변글인가를 알려주고; 원본글과 답변글을 구분하는 값
	    private int bbs_level; //답변글 정렬순서
	    private String bbs_date;//글 등록 날짜
	    
	    
	    //페이징(쪽나누기) 관련 변수
	    private int startrow; //시작행 번호
	    private int endrow; //끝행번호
	    
	    //검색관련 변수
	    private String find_field; //검색 필드
	    private String find_name; //검색어
	    
	    
}
