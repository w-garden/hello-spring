package com.naver.vo;

import lombok.Getter;
import lombok.Setter;

@Setter //setter()메서드 자동제공
@Getter //getter()메서드 자동제공
public class BbsVO {//BBS테이블의 컬럼명과 빈클래스의 변수명은 되도록이면 같게 하고, 중간 자료 저장빈 클래스 역할.자료실 저장빈 클래스.

	private int bbs_no;
	private String bbs_name;
	private String bbs_title;
	private String bbs_pwd;
	private String bbs_cont;
	private String bbs_file;
	private int bbs_hit;
	private int bbs_ref; //글 그룹번호
	private int bbs_step;//원본글과 답변글을 구분하는 번호값이면서 몇번째 답변글인가를 알려준다. 원본글이면 0,첫번째 답변글이면 1,
	//두번째 답변글이면 2
	private int bbs_level;//답변글 정렬순서
	private String bbs_date;
	
    //페이징(쪽나누기) 관련 변수
	private int startrow; //시작행 번호
	private int endrow;//끝행 번호
	
	//검색관련 변수
	private String find_field;//검색 필드
	private String find_name;//검색어
}












