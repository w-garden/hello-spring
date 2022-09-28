package net.daum.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.ReplyVO;


@Repository //@Repository 애노테이션은 DAO를 스프링에서 인식하게 한다.
public class ReplyDAOImpl implements ReplyDAO {
	
	@Autowired //자동 의존성 주입(DI)
	private SqlSession sqlSession;//mybatis 쿼리문 실행 sqlSession

	@Override
	public void insertReply(ReplyVO vo) {
		this.sqlSession.insert("reply_in", vo);
	}//댓글저장

	@Override
	public List<ReplyVO> replyList(int bno) {
		return this.sqlSession.selectList("reply_list", bno);
	}//댓글목록

	@Override
	public void updateReply(ReplyVO vo) {
		this.sqlSession.update("reply_edit", vo);
		
	}

	@Override
	public void delReply(int rno) {
		this.sqlSession.delete("reply_del", rno);
		
	}//댓글삭제

	@Override
	public int getBno(int rno) {
		return this.sqlSession.selectOne("reply_bno", rno); //selectOne()메서드는 단 하나의 레코드 값만 반환, reply_bno는 매퍼태그에서 설정할 유일한 아이디명
	}//댓글 번호에 해당하는 게시판 번호 구하기
	
	
}
