package com.naver.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.AdminVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertAdmin(AdminVO ab) {
	   this.sqlSession.insert("admin_in",ab);
	   //admin_in이 매퍼태그에서 설정할 유일한  insert 아이디명
	}//관리자 정보 저장

	@Override
	public AdminVO adminLogin(String admin_id) {
		return this.sqlSession.selectOne("admin_login",admin_id);
	}//관리자 로그인 인증	
}
