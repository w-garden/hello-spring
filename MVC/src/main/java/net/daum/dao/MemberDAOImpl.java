package net.daum.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.PStmtKey;

import net.daum.vo.MemberVO;
import net.daum.vo.ZipCodeVO;

public class MemberDAOImpl { //회원관리 JDBC
	
	Connection con= null;
	PreparedStatement pt=null;
	ResultSet rs= null;
	DataSource ds = null;

	String sql ="";
	
	public MemberDAOImpl() {
		try {
			Context context= new InitialContext();
			ds= (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		}catch(Exception e) {e.printStackTrace();}
		
	}//생성자
	
	//아이디 중복검색
	public MemberVO idCheck(String id) {
		MemberVO m = null;
		try {
			con=ds.getConnection();
			sql="select * from memberT where mem_id=?";
			pt =con.prepareStatement(sql);
			
			pt.setString(1, id); //쿼리문의 첫번째 물음표에 문자열로 아이디저장
			rs=pt.executeQuery(); //검색 쿼리문 수행후 결과 레코드를 rs 에 저장
			
			if(rs.next()) {
				m=new MemberVO();
				m.setMem_id(rs.getString("mem_id"));
			}
			
		}catch(Exception e) {e.printStackTrace();}
		finally {
			try {
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}//idCheck()
		
		return m;
	}
	
	//회원저장
	public int insertMember(MemberVO m) {
		int re=0; //삭제 실패시 반환값
		
		
		try {
			con=ds.getConnection();
			sql="insert into memberT( mem_id, mem_pwd,mem_name, mem_zip, mem_zip2,"
					+"mem_addr,mem_addr2,mem_phone01,mem_phone02,mem_phone03,mail_id,mail_domain,"
					+"mem_file,mem_date,mem_state) values(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,1)";
			
			
			pt=con.prepareStatement(sql);
			pt.setString(1, m.getMem_id());
			pt.setString(2, m.getMem_pwd());
			pt.setString(3, m.getMem_name());
			pt.setString(4, m.getMem_zip());
			pt.setString(5, m.getMem_zip2());
			pt.setString(6, m.getMem_addr());
			pt.setString(7, m.getMem_addr2());
			pt.setString(8, m.getMem_phone01());
			pt.setString(9, m.getMem_phone02());
			pt.setString(10, m.getMem_phone03());
			pt.setString(11, m.getMail_id());
			pt.setString(12, m.getMail_domain());
			pt.setString(13, m.getMem_file());
			
			re=pt.executeUpdate(); //저장 쿼리문 수행후 성공한 레코드 행의 개수를 반환
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pt!= null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return re;
	} //insertMember()
	
	//우편검색 
	public List<ZipCodeVO> zipFind(String dong){
		List<ZipCodeVO> zcodeList =new ArrayList<>();
		
		try {
			con = ds.getConnection();
			sql ="select * from zipcode where dong like ?"; 
			//like 는 검색연산자로 ~와 비슷한 문자를 찾아서 검색
			
			pt =con.prepareStatement(sql);
			pt.setString(1, dong);
			rs=pt.executeQuery();
			
			
			while(rs.next()) {
				ZipCodeVO z=new ZipCodeVO();
				z.setZipcode(rs.getString("zipcode"));
				z.setSido(rs.getString("sido"));
				z.setGugun(rs.getString("gugun"));
				z.setDong(rs.getString("dong"));
				z.setBunji(rs.getString("bunji"));
				
				zcodeList.add(z); //컬렉션에 추가
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return zcodeList;
	}//zipFind()
	
	
	public MemberVO pwdMember(MemberVO m) {
		MemberVO pm = null;
		try {
			con=ds.getConnection();
			sql="select mem_pwd from memberT where mem_id=? and mem_name=?";
			
			pt=con.prepareStatement(sql);
			
			pt.setString(1, m.getMem_id());
			pt.setString(2, m.getMem_name());
			rs=pt.executeQuery();
			
			if(rs.next()) {
				pm =new MemberVO();
				pm.setMem_pwd(rs.getString("mem_pwd"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return pm;
		
	}
	
	
	//로그인 체크
	public MemberVO loginCheck(String id) {
		MemberVO m= null;
		try {
			con = ds.getConnection();
			sql="select * from memberT where mem_id=? and mem_state=1";
			pt=con.prepareStatement(sql);
			pt.setString(1, id);
			
			rs=pt.executeQuery();
			
			if(rs.next()) {
				m =new MemberVO();
				m.setMem_pwd(rs.getString("mem_pwd"));
				m.setMem_file(rs.getNString("mem_file")); //프로필 사진 경로를 저장
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs != null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		return m;
	}
	//아이디에 해당하는 회원정보 가져오기
	public MemberVO getMember(String id) {
		MemberVO m = null;
		try {
			con = ds.getConnection();
			sql ="select * from memberT where mem_id=?";
			pt = con.prepareStatement(sql);
			pt.setString(1, id);
			rs= pt.executeQuery();
			
			if(rs.next()) {
				m= new MemberVO();
				m.setMem_id(rs.getString("mem_id"));
				m.setMem_pwd(rs.getString("mem_pwd"));
				m.setMem_name(rs.getString("mem_name"));
				m.setMem_zip(rs.getString("mem_zip"));
				m.setMem_zip2(rs.getString("mem_zip2"));
				m.setMem_addr(rs.getString("mem_addr"));
				m.setMem_addr2(rs.getString("mem_addr2"));
				m.setMem_phone01(rs.getString("mem_phone01"));
				m.setMem_phone02(rs.getString("mem_phone02"));
				m.setMem_phone03(rs.getString("mem_phone03"));
				m.setMail_id(rs.getString("mail_id"));
				m.setMail_domain(rs.getString("mail_domain"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pt != null) pt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return m;
	}
	//회원정보수정
	public void updateMember(MemberVO m) {
		try {
			con=ds.getConnection();
			sql="update memberT set mem_pwd=?, mem_name=?, mem_zip=?, mem_zip2=?, mem_addr=?, mem_addr2=?, mem_phone01=?, mem_phone02=?, mem_phone03=?, mail_id=?, mail_domain=?"
			+"where mem_id=?";
			pt=con.prepareStatement(sql);
			pt.setString(1, m.getMem_pwd());
			pt.setString(2, m.getMem_name());
			pt.setString(3, m.getMem_zip());
			pt.setString(4, m.getMem_zip2());
			pt.setString(5, m.getMem_addr());
			pt.setString(6, m.getMem_addr2());
			pt.setString(7, m.getMem_phone01());
			pt.setString(8, m.getMem_phone02());
			pt.setString(9, m.getMem_phone03());
			pt.setString(10, m.getMail_id());
			pt.setString(11, m.getMail_domain());
			pt.setString(12, m.getMem_id());
			
			pt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pt != null) pt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	//아이디를 기준으로 탈퇴사유, mem_state=2, 탈퇴날짜를 수정되게 하면서 탈퇴시킨다.
	public void deleteMember(MemberVO dm) {
		try {
			con= ds.getConnection();
			sql="update memberT set mem_state=2, mem_delcont=?, mem_deldate=sysdate where mem_id=?";
			pt=con.prepareStatement(sql);
			
			pt.setString(1, dm.getMem_delcont());
			pt.setString(2, dm.getMem_id());

			pt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pt != null) pt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

}
