package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.MemberVO;
import com.naver.vo.ZipcodeVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlsession;

	@Override
	public MemberVO idCheck(String id) {
		return sqlsession.selectOne("m_idcheck", id);
	}//중복 아이디 검색

	@Override
	public List<ZipcodeVO> zipFind(String dong) {
		return sqlsession.selectList("m_zip",dong);
	}//우편주소 검색

	@Override
	public void insertMember(MemberVO m) {
		sqlsession.insert("m_join",m);
	}//회원저장

	@Override
	public MemberVO pwdMember(MemberVO m) {
		return sqlsession.selectOne("p_find", m);
	}//비번찾기

	@Override
	public void updatePwd(MemberVO m) {
		sqlsession.update("pwd_edit", m);
	}

	@Override
	public MemberVO loginCheck(String login_id) {
		return sqlsession.selectOne("m_loginCK",login_id);
	}//로그인 인증

	@Override
	public MemberVO getMember(String id) {
		return sqlsession.selectOne("m_edit",id);
	}//아이디에 해당하는 회원 정보 보기
}
