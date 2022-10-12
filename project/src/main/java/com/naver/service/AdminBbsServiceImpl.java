package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.AdminBbsDAO;
import com.naver.vo.BbsVO;

@Service
public class AdminBbsServiceImpl implements AdminBbsService {

	@Autowired
	private AdminBbsDAO adminBbsDao;

	@Override
	public int getListCount(BbsVO b) {
		return this.adminBbsDao.getListCount(b);
	}

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return this.adminBbsDao.getBbsList(b);
	}

	@Override
	public void insertBbs(BbsVO b) {
		this.adminBbsDao.insertBbs(b);		
	}

	@Override
	public BbsVO getBbsCont(int no) {
		return adminBbsDao.getBbsCont(no);
	}

	@Override
	public void updateBbs(BbsVO b) {
		adminBbsDao.updateBbs(b);
	}

	@Override
	public void delBbs(int no) {
		adminBbsDao.deleteBbs(no);
	}
}








