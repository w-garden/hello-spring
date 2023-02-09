package net.daum.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.daum.vo.GuVO;

public class GuDAOimpl {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	DataSource ds = null;

	String sql = null;

	public GuDAOimpl() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/xe");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public int insertGu(GuVO g) {
		int re = 0;

		try {
			conn = ds.getConnection();

			sql = "insert into gutest values(g_no_seq.nextval, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getTitle());
			pstmt.setString(2, g.getCont());

			re = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return re;
	}
	
	
	//방명록 목록
	public List<GuVO> getGuList(){
		List<GuVO> glist =new ArrayList<>();
		try {
			conn=ds.getConnection();
			sql="select * from gutest order by no desc";
			
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				GuVO g =new GuVO();
				g.setNo(rs.getInt("no"));
				g.setTitle(rs.getString("title"));
				g.setCont(rs.getString("cont"));
				g.setRegDate(rs.getString("regDate"));
				
				glist.add(g);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return glist;
	}
	
}
