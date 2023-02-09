package net.daum.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class OracleConTest {

	private static final String Driver = "oracle.jdbc.OracleDriver";//oracle.jdbc는 패키지명, OracleDriver는 jdbc 드라이버
	//클래스명
	private static final String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";//오라클 접속 주소, 1521은 포트번호, xe는 데이터베이스
	//명
	private static final String user="week";//오라클 접속 사용자
	private static final String password="week";//비번
	
	@Test //JUnit 연습용 테스트 애노테이션(@)
	public void testOracleCon() throws Exception{
		Class.forName(Driver);//jdbc드라이버 클래스 로드
		
		try(Connection con=DriverManager.getConnection(url, user, password)){
			/* 자바 7버전이후 AutoCloseable 인터페이스를 구현 상속받은 자손은 try()내에서 con을 생성하면 finally문에서 명시적으로 close()
			 * 하지 않아도 자동으로 닫힌다.
			 */
			System.out.println(con);
		}catch(Exception e) {e.printStackTrace();}
	}
}






