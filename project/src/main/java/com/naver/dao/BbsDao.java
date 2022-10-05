package com.naver.dao;

import java.util.List;

import com.naver.vo.BbsVO;

public interface BbsDao {

	void insertBbs(BbsVO b);

	int getRowCount(BbsVO b);

	List<BbsVO> getBbsList(BbsVO b);

	void updateHit(int bbs_no);

	BbsVO getBbs_Cont(int bbs_no);

	void updateLevel(BbsVO rb);

	void replyBbs(BbsVO rb);

}
