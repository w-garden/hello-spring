package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.MemberDAO;
import com.naver.vo.MemberVO;
import com.naver.vo.ZipcodeVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDao;

	@Override
	public MemberVO idCheck(String id) {
		return memberDao.idCheck(id);
	}

	@Override
	public List<ZipcodeVO> zipFind(String dong) {
		return memberDao.zipFind(dong);
	}

	@Override
	public void insertMember(MemberVO m) {
		memberDao.insertMember(m);
	}

	@Override
	public MemberVO pwdMember(MemberVO m) {
		return memberDao.pwdMember(m);
	}

	@Override
	public void updatePwd(MemberVO m) {
		memberDao.updatePwd(m);
	}

	@Override
	public MemberVO loginCheck(String login_id) {
		return memberDao.loginCheck(login_id);
	}

	@Override
	public MemberVO getMember(String id) {
		return memberDao.getMember(id);
	}
}
