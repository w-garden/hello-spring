package com.naver.service;

import java.util.List;

import com.naver.vo.GongjiVO;

public interface AdminGongjiService {

	int getListCount(GongjiVO g);
	List<GongjiVO> getGongjiList(GongjiVO g);
	void insertG(GongjiVO g);
	GongjiVO getGongjiCont(int no);
	void editGongji(GongjiVO g);
	void delG(int no);	
}
