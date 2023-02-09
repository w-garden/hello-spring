package com.naver.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.naver.dao.GongjiDAO;
import com.naver.vo.GongjiVO;

@Service
public class GongjiServiceImpl implements GongjiService {

	@Inject
	private GongjiDAO gongjiDAO;

	@Override
	public List<GongjiVO> getList() {
		return this.gongjiDAO.getList();
	}

	//트랜잭션 적용대상
	@Override
	public GongjiVO getGCont(int gongji_no) {
        this.gongjiDAO.updateHit(gongji_no);//조회수 증가		
		return this.gongjiDAO.getGCont(gongji_no);
	}
}


















