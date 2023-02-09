package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter //lombok라이브러리 에서 setter()메서드 자동 제공
@Getter //lombok라이브러리 에서 getteR()메서드 자동 제공
public class SampleVO { //데이터 저장빈 클래스
   
   private int mno; //변수명이 JSON 데이터의 키이름이 된다.
   private String firstName; //이름
   private String lastName; //성
}