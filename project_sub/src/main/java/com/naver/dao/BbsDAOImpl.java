package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.BbsVO;

@Repository //@Repository 애노테이션을 설정하면 스프링 MVC에서 모델 DAO로 인식
public class BbsDAOImpl implements BbsDAO {
	
	@Autowired //의존성 주입(DI:Dependency Injection)
	private SqlSession sqlSession;//mybatis 쿼리문 수행 sqlSession 생성

	@Override
	public void insertBbs(BbsVO b) {
		this.sqlSession.insert("bbs_in",b);//mybatis에서 bbs_in은 매퍼태그에서 설정할 유일한 아이디명, insert()메서드로 레코드를 저장		
	}//자료실 저장

	@Override
	public int getRowCount(BbsVO b) {
		return sqlSession.selectOne("bbs_count",b);//mybatis에서 selectOne()메서드는 단 한개의 레코드만 반환, bbs_count는 mybatis
		//매퍼태그에서 설정할 유일 아이디명
	}//검색 전후 레코드 개수

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return this.sqlSession.selectList("bbs_list",b);//mybatis에서 selectList()메서드는 하나이상의 레코드값을 검색해서 컬렉션으로
		//반환
	}//검색 전후 목록

	@Override
	public void updateHit(int bbs_no) {
		this.sqlSession.update("bbs_hi",bbs_no);//update()메서드로 레코드 수정, bbs_hi는 유일한 아이디명		
	}//조회수 증가

	@Override
	public BbsVO getBbs_Cont(int bbs_no) {
        return sqlSession.selectOne("bbs_co",bbs_no);
	}//내용보기

	@Override
	public void updateLevel(BbsVO rb) {
		this.sqlSession.update("levelUp",rb);
	}//답변 레벨 증가

	@Override
	public void replyBbs(BbsVO rb) {
       	this.sqlSession.insert("reply_in2",rb);	
	}//답변 저장

	@Override
	public void editBbs(BbsVO eb) {
		this.sqlSession.update("bbs_edit", eb);		
	}//자료실 수정

	@Override
	public void delBbs(int bbs_no) {
	    this.sqlSession.delete("bbs_del",bbs_no);//delete()메서드로 레코드 삭제, bbs_del은 매퍼태그에서 설정할 유일한 아이디명			
	}//자료실 삭제
}










