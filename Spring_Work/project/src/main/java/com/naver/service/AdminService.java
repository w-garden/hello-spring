package com.naver.service;

import com.naver.vo.AdminVO;

public interface AdminService {

	void insertAdmin(AdminVO ab);
	AdminVO adminLogin(String admin_id);

}
