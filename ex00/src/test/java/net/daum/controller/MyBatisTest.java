package net.daum.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MyBatisTest {

	@Inject //자동의존성 주입(@Autowired)
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void testFactory() {
		System.out.println(sqlFactory);
	}
	
	@Test
	public void testSqlsession() throws Exception{
        try(SqlSession sqlSession=sqlFactory.openSession()){
        	//sqlSession은 마이바티스에서 쿼리문을 수행하는 역할
        	System.out.println(sqlSession);
        }catch(Exception e) {e.printStackTrace();}
	}
}









