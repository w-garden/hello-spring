package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;

@Repository
public class MemberMapperDAOImpl implements MemberMapper {//모델DAOImpl
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberVO read(String userid) {
		return this.sqlSession.selectOne("read_member",userid); 
		//mybatis에서 selectOne()메서드는 단 한개의 레코드값만 반환, read_member는 매퍼태그에서 설정할 유일한 아이디명
	}
}
