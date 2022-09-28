package net.daum.service;

import java.util.List;

import net.daum.vo.ReplyVO;

public interface ReplyService {

	void insertReply(ReplyVO vo);

	List<ReplyVO> replyList(int bno);

	void updateReply(ReplyVO vo);

	void delReply(int rno);

}
