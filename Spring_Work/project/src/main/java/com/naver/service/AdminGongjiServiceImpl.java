package com.naver.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.naver.dao.AdminGongjiDAO;
import com.naver.vo.GongjiVO;

@Service
public class AdminGongjiServiceImpl implements AdminGongjiService {

	@Inject
	private AdminGongjiDAO adminGongjiDAO;

	@Override
	public int getListCount(GongjiVO g) {
		return this.adminGongjiDAO.getListCount(g);
	}

	@Override
	public List<GongjiVO> getGongjiList(GongjiVO g) {
		return this.adminGongjiDAO.getGongjiList(g);
	}

	@Override
	public void insertG(GongjiVO g) {
		this.adminGongjiDAO.insertG(g);		
	}

	@Override
	public GongjiVO getGongjiCont(int no) {
		return this.adminGongjiDAO.getGongjiCont(no);
	}

	@Override
	public void editGongji(GongjiVO g) {
		this.adminGongjiDAO.editGongji(g);		
	}

	@Override
	public void delG(int no) {
        this.adminGongjiDAO.delG(no);		
	}	
}









