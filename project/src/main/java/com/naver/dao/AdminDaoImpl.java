package com.naver.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.AdminVO;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertAdmin(AdminVO ab) {
		sqlSession.insert("admin_in",ab);
	}

	@Override
	public AdminVO adminLogin(String admin_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin_login",admin_id);
	}//관리자 로그인 인증
}
