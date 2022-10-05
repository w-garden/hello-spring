package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.BbsVO;

@Repository
public class BbsDaoImpl implements BbsDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertBbs(BbsVO b) {
		sqlSession.insert("bbs_in", b);
	}//자료실 저장

	@Override
	public int getRowCount(BbsVO b) {
		return sqlSession.selectOne("bbs_count", b);
	}//검색전후 레코드 개수

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return sqlSession.selectList("bbs_list",b); //mybatis에서 selectList()메서드는 하나 이상의 레코드값을 검색해서 컬렉션으로 반환
	}//검색전후 목록

	@Override
	public void updateHit(int bbs_no) {
		sqlSession.update("bbs_hi", bbs_no);
		
	}//조회수 증가

	@Override
	public BbsVO getBbs_Cont(int bbs_no) {
		return sqlSession.selectOne("bbs_co", bbs_no);
	}//내용보기

	@Override
	public void updateLevel(BbsVO rb) {
		sqlSession.update("levelUP", rb);
		
	}//답변 레벨 증가

	@Override
	public void replyBbs(BbsVO rb) {
		sqlSession.insert("reply_in2", rb);
	} //답변 저장
}
