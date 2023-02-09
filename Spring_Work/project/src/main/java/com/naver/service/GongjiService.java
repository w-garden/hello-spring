package com.naver.service;

import java.util.List;

import com.naver.vo.GongjiVO;

public interface GongjiService {

	List<GongjiVO> getList();
	GongjiVO getGCont(int gongji_no);

}
