package net.daum.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {//tbl_member테이블의 데이터 저장빈 클래스
	
	private String userid;
	private String userpw;
	private String userName;
	private boolean enabled;
	
	private Date regDate;
	private Date updateDate;
	private List<AuthVO> authList; //권한 목록
}
	