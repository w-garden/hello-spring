package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.AdminBbsDAO;
import com.naver.vo.BbsVO;

@Service
public class AdminBbsServiceImpl implements AdminBbsService{
	
	@Autowired
	private AdminBbsDAO adminBbdDao;

	@Override
	public int getListCount(BbsVO b) {
		return adminBbdDao.getListCount(b);
	}

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return adminBbdDao.getBbsList(b);
	}

	@Override
	public void insertBbs(BbsVO b) {
		adminBbdDao.insertBbs(b);
	}
}
