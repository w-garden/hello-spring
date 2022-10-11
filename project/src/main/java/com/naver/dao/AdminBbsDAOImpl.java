package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.BbsVO;

@Repository
public class AdminBbsDAOImpl implements AdminBbsDAO {//관리자 자료실 모델 DAO

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int getListCount(BbsVO b) {
		return this.sqlSession.selectOne("abbs_count",b);
	}//관리자 자료실 검색전후 목록

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return this.sqlSession.selectList("abbs_list",b);
	}//관리자 자료실 검색전후 목록

	@Override
	public void insertBbs(BbsVO b) {
		this.sqlSession.insert("abbs_in", b);
	}//관리자 자료실 저장
}










