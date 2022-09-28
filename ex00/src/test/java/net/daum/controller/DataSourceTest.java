package net.daum.controller;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class DataSourceTest {

	@Autowired //자동의존성 주입
	private DataSource ds; //커넥션 풀 관리 ds생성
	/* 스프링에서 의존성 주입 방법)
	 *  1.생성자를 통한 의존성 주입 2.setter()메서드를 통한 의존성 주입
	 */
	
	@Test
	public void testOracleCon2() throws Exception{
		try(Connection con=ds.getConnection()){
			System.out.println(con);
		}catch(Exception e) {e.printStackTrace();}
	}
}







