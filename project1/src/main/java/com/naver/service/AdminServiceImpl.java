package com.naver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.AdminDao;
import com.naver.vo.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	@Override
	public void insertAdmin(AdminVO ab) {
		adminDao.insertAdmin(ab);
	}

	@Override
	public AdminVO adminLogin(String admin_id) {
		return adminDao.adminLogin(admin_id);
	}
}
