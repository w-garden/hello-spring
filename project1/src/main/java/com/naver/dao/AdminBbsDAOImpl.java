package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.BbsVO;

@Repository
public class AdminBbsDAOImpl implements AdminBbsDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int getListCount(BbsVO b) {
		return sqlSession.selectOne("abbs_count", b);
	}

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return sqlSession.selectList("abbs_list",b);
	}

	@Override
	public void insertBbs(BbsVO b) {
		sqlSession.insert("abbs_in", b);
	}
}
