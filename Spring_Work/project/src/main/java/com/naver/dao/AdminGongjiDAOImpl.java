package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.GongjiVO;

@Repository
public class AdminGongjiDAOImpl implements AdminGongjiDAO {

	@Autowired
	private SqlSession sqlSession;//mybatis 쿼리문 실행 객체
	//를 생성, 자동의존성 주입

	@Override
	public int getListCount(GongjiVO g) {
		return this.sqlSession.selectOne("ag_count",g);
	}//검색 전후 레코드 개수

	@Override
	public List<GongjiVO> getGongjiList(GongjiVO g) {
		return this.sqlSession.selectList("ag_list",g);
	}//검색 전후 공지목록

	@Override
	public void insertG(GongjiVO g) {
       	this.sqlSession.insert("ag_in",g);	
	}

	@Override
	public GongjiVO getGongjiCont(int no) {
		return this.sqlSession.selectOne("ag_cont",no);
	}

	@Override
	public void editGongji(GongjiVO g) {
		this.sqlSession.update("ag_edit",g);		
	}

	@Override
	public void delG(int no) {
		this.sqlSession.delete("ag_del",no);		
	}
}













