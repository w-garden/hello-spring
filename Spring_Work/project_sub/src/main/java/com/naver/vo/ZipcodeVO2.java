package com.naver.vo;

import lombok.Data;

@Data
public class ZipcodeVO2 { //ZipcodeVO 빈클래스에 저장된 값을 한번 더 가공해서 저장하기 위한 빈클래스
	private String zipcode; //우편번호
	private String addr; //시도 구군등

}
