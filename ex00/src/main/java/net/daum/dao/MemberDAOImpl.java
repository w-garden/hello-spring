package net.daum.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;

@Repository //@Repository 애노테이션은 DAO를 스프링에서 인식하게 한다.
public class MemberDAOImpl implements MemberDAO {

	@Inject //자동 의존성 주입(DI)
	private SqlSession sqlSession;//mybatis 쿼리문 실행 sqlSession
	
	@Override
	public void insertMember(MemberVO m) {
		this.sqlSession.insert("m_in" , m);
		/* 1. m_in은 유일한(중복하면 안됨) mybatis 매퍼태그 아이디명
		 * 2. mybatis 쿼리문 실행 메서드 종류
		 *    가. insert() : 레코드 저장
		 *    나. update() : 레코드 수정
		 *    다. delete() : 레코드 삭제
		 *    라. selectOne() : 단 한개의 레코드만 검색
		 *    마. selectList() : 하나이상의 레코드를 검색
		 */
	}
}
