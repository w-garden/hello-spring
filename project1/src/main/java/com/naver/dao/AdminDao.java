package com.naver.dao;

import com.naver.vo.AdminVO;

public interface AdminDao {

	void insertAdmin(AdminVO ab);

	AdminVO adminLogin(String admin_id);

}
