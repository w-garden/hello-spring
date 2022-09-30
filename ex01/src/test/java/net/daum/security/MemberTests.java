package net.daum.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
public class MemberTests {

		@Setter(onMethod_ = @Autowired) //setter() 메서드를 통한 DI 의존성 주입
		public PasswordEncoder pwencoder;
		
		@Setter(onMethod_ =@Autowired)
		public DataSource ds; //커넥션 풀 관리 JNDI DBCP ds 생성
		
		@Test
		public void testInsertMember() {
			String sql ="insert into tbl_member(userid, userpw, username) values(?, ?, ?)";
			
			for(int i=0; i<100; i++) {
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con= ds.getConnection();
					pstmt= con.prepareStatement(sql);
					
					pstmt.setString(2, pwencoder.encode("pw"+i)); //pw0~pw99까지 비번이 암호화됨
					
					
					if(i<80) {//0~79
						pstmt.setString(1, "user"+i); //user0~user79
						pstmt.setString(3, "일반사용자"+i); //일반사용자0~일반사용자79
					}
					else if(i<90) {
						pstmt.setString(1, "manager"+i); //manager80~manager89
						pstmt.setString(3, "운영자"+i); //운영자80~운영자89
					}
					else {
						pstmt.setString(1, "admin"+i); //admin90~admin99
						pstmt.setString(3, "관리자"+i);//관리자90~관리자99
						
					}
					pstmt.executeUpdate(); //저장 쿼리문 100번 수행
				} catch (Exception e) {
				}finally {
					try {
						if(pstmt != null) pstmt.close();
						if(con != null) con.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
			}//for
		}//testInsertMember()
}
