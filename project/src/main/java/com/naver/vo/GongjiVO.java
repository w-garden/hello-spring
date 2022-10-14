package com.naver.vo;

import lombok.Data;

@Data
public class GongjiVO {//공지 사항 데이터 저장빈 클래스

	/*
	 * Mybatis 를 사용할려면 테이블 컬럼명과 빈클래스 변수명을
	 * 같게 한다.
	 */
	private int gongji_no;
	private String gongji_name;
	private String gongji_title;
	private String gongji_pwd;
	private String gongji_cont;
	private int gongji_hit;
	private String gongji_date;
	
	//페이징 관련변수
	private int startrow;//시작행 번호
	private int endrow;//끝행 번호
	
	//검색어와 검색필드 관련변수
	private String find_field;//검색필드
	private String find_name;//검색어	
	
}










