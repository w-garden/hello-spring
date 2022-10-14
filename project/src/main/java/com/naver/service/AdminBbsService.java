package com.naver.service;

import java.util.List;

import com.naver.vo.BbsVO;

public interface AdminBbsService {

	int getListCount(BbsVO b);
	List<BbsVO> getBbsList(BbsVO b);
	void insertBbs(BbsVO b);
	BbsVO getBbsCont(int no);
	void udpateBbs(BbsVO b);
	void delBbs(int no);

}
