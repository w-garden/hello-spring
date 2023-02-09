package com.naver.service;

import java.util.List;

import com.naver.vo.MemberVO;
import com.naver.vo.ZipcodeVO;

public interface MemberService {

	MemberVO idCheck(String id);
	List<ZipcodeVO> zipFind(String dong);
	void insertMember(MemberVO m);
	MemberVO pwdMember(MemberVO m);
	void updatePwd(MemberVO m);
	MemberVO loginCheck(String login_id);
	MemberVO getMember(String id);
	void updateMember(MemberVO m);
	void delMem(MemberVO dm);

}
