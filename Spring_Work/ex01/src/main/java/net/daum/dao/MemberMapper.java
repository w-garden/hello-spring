package net.daum.dao;

import net.daum.vo.MemberVO;

public interface MemberMapper {//모델 DAO 인터페이스

	MemberVO read(String userid); 
	
	
}
