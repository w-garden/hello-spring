package com.naver.vo;

import lombok.Data;

@Data
public class MemberVO {
	private String mem_id; //회원 아이디
	private String mem_pwd;  //비밀 번호
	private String mem_name;  //회원 이름
	private String mem_zip;  //우편 번호
	private String mem_zip2;  //우편 번호
	private String mem_addr;  //주소
	private String mem_addr2; //나머지 주소
	private String mem_phone01; //첫번째 자리 폰번호
	private String mem_phone02;  //두번째 자리 폰번호
	private String mem_phone03;  //세번째 자리 폰번호
	private String mail_id; //메일 아이디
	private String mail_domain;//메일 도메인
	private String mem_date;  //가입날짜
	private int mem_state;  //가입회원이면 1, 탈퇴회원이면 2
	private String mem_delcont;  //탈퇴사유
	private String mem_deldate; // 탈퇴날짜
	
	
	//관리자 회원관리의 회원목록에서 페이징 즉 쪽 나누기 관련 변수
	private int startrow; //시작행 번호
	private int endrow; //끝행 번호
	
	//관리자 회원목록 검색기능 관련 변수 : 검색 필드와 검색어
	
	private String find_name; //검색어
	private String find_field; //검색 필드
}
