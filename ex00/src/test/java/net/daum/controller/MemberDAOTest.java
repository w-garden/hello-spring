package net.daum.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.daum.dao.MemberDAO;
import net.daum.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MemberDAOTest {

	@Autowired //자동의존성 주입
	private MemberDAO dao;
	
	@Test
	public void testInsertMember() throws Exception{
		MemberVO m=new MemberVO();
		
		m.setUserid("bbbbb");//회원아이디
		m.setUserpw("99999");//비번
        m.setUsername("홍길동");
        m.setEmail("hong@daum.net");
        
        dao.insertMember(m);//회원 저장
	}
}









