package com.naver.vo;

import lombok.Data;

@Data //setter(),getter(),toString()메서드 등을 자동제공하고, 기본생성자까지 제공.
public class ZipcodeVO {//우편주소를 저장하는 데이터 저장빈 클래스 : zipcode 테이블의 컬럼명과 빈클래스 변수명을 되도록이면 일치시킨다.

	private int no;
	private String zipcode;//우편번호
	private String sido;//시도
	private String gugun;//구군
	private String dong;//동
	private String bunji;//번지
}
