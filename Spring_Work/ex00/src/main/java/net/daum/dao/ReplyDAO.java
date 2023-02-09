package net.daum.dao;

import java.util.List;

import net.daum.vo.ReplyVO;

public interface ReplyDAO {

	void insertReply(ReplyVO vo);

	List<ReplyVO> replyList(int bno);

	void updateReply(ReplyVO vo);

	void delReply(int rno);

	int getBno(int rno);

}
