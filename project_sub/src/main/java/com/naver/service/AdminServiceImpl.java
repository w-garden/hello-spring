package com.naver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.AdminDAO;
import com.naver.vo.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;

	@Override
	public void insertAdmin(AdminVO ab) {
		this.adminDAO.insertAdmin(ab);
	}

	@Override
	public AdminVO adminLogin(String admin_id) {
		return this.adminDAO.adminLogin(admin_id);
	}
}
