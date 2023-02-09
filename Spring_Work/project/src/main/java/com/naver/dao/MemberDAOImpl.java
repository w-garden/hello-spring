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
	private SqlSession sqlSession;//mybatis쿼리문 수행 sqlSession

	@Override
	public MemberVO idCheck(String id) {
		return this.sqlSession.selectOne("m_idcheck", id);
	}//중복 아이디 검색

	@Override
	public List<ZipcodeVO> zipFind(String dong) {
    	return this.sqlSession.selectList("m_zip",dong);
	}//우편주소 검색

	@Override
	public void insertMember(MemberVO m) {
		sqlSession.insert("m_in",m);		
	}//회원 저장

	@Override
	public MemberVO pwdMember(MemberVO m) {		
		return this.sqlSession.selectOne("p_find",m) ;
	}//비번찾기로 인한 회원정보 검색

	@Override
	public void updatePwd(MemberVO m) {
		this.sqlSession.update("p_edit",m);		
	}//임시 비번 수정

	@Override
	public MemberVO loginCheck(String login_id) {
		return sqlSession.selectOne("m_loginCk",login_id);
	}//로그인 인증 

	@Override
	public MemberVO getMember(String id) {
		return this.sqlSession.selectOne("m_edit",id);
	}//아이디에 해당하는 회원정보 보기

	@Override
	public void updateMember(MemberVO m) {
		this.sqlSession.update("medit_ok",m);
	}//정보 수정

	@Override
	public void delMem(MemberVO dm) {
		this.sqlSession.update("m_del",dm);
	}//회원탈퇴=>update SQL문 활용
}




