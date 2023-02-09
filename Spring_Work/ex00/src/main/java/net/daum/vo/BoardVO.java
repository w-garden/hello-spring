package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardVO { //되도록이면 테이블 컬럼명과 일치하는 변수명을 가진 빈클래스를 생성

	private int bno;
	private String writer;
	private String title;
	private String content;
	private int viewcnt;//조회수
	private String regdate;//등록날짜
	private int replycnt; //댓글수 카운터
	
	//페이징 관련 변수(쪽나누기)
	private int startrow;//시작행번호
	private int endrow;//끝행번호
}
