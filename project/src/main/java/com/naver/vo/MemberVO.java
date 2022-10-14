package com.naver.vo;

import lombok.Data;

@Data //setter(),getter(),toString(),기본생성자등을 묵시적 제공
public class MemberVO { //테이블 컬럼(필드)명과 빈클래스 변수명을 되도록이면 일치시킨다. 이유는 코드라인을 줄일 수 있다.

	private String mem_id;//회원아이디
	private String mem_pwd;//비번
	private String mem_name;//회원이름
	private String mem_zip;//우편번호
	private String mem_zip2;//우편번호
	private String mem_addr;//주소
	private String mem_addr2;//나머지 주소
    private String mem_phone01;//폰번호
    private String mem_phone02;
    private String mem_phone03;
    private String mail_id;
    private String mail_domain;
    private String mem_date;//가입날짜
    private int mem_state;//가입회원이면 1, 탈퇴회원이면 2
    private String mem_delcont;//탈퇴 사유
    private String mem_deldate;//탈퇴 날짜
    
    //관리자 회원관리의 회원목록에서 페이징 즉 쪽나누기 관련변수
    private int startrow;//시작행 번호
    private int endrow;//끝행번호
    
    //관리자 회원목록 검색기능 관련 변수: 검색 필드와 섬색어
    private String find_name;//검색어
    private String find_field;//검색 필드
}










