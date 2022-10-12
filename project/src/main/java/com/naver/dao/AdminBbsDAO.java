package com.naver.dao;

import java.util.List;

import com.naver.vo.BbsVO;

public interface AdminBbsDAO {

	int getListCount(BbsVO b);
	List<BbsVO> getBbsList(BbsVO b);
	void insertBbs(BbsVO b);
	BbsVO getBbsCont(int no);
	void updateBbs(BbsVO b);
	void deleteBbs(int no);
}
