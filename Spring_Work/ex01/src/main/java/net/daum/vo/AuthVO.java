package net.daum.vo;

import lombok.Data;

@Data
public class AuthVO {//tbl_member_auth 테이블의 데이터 저장빈 클래스
	
	private String userid;
	private String auth;
}
