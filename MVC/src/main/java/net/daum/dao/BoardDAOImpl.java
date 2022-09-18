package net.daum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.daum.vo.BoardVO;

public class BoardDAOImpl {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	DataSource ds=null;
	String sql="";
	
	public BoardDAOImpl() {
		try {
			Context context = new InitialContext();
			ds= (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void close(Connection conn) {
		try {
			if(conn!= null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void close(PreparedStatement pstmt) {
		try {
			if(pstmt!= null) pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void close(ResultSet rs) {
		try {
			if(rs!= null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//검색전, 검색후 레코드 개수
	public int getListCount(BoardVO findB) {
		int count=0;
		try {
			conn=ds.getConnection();
			sql ="select count(board_no) from boardT ";
			if(findB.getFind_field()==null) { //검색전 총 레코드 개수
				sql+="";
			}
			else if(findB.getFind_field().equals("board_title")) { //검색 필드가 글제목인 경우
				sql+=" where board_title like ?";
			}else if(findB.getFind_field().equals("board_cont")) {//검색 필드가 글내용인 경우
				sql+=" where board_cont like ?"; //like는 ~와 비슷한 문자를 찾는다. 검색연산자
			}
			pstmt=conn.prepareStatement(sql);
			
			if(findB.getFind_field() != null) { //검색필드가 있는 경우
				pstmt.setString(1,findB.getFind_name()); //쿼리문의 첫번째 물음표에 문자열로 검색어를 저장
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1); //검색 전후 레코드 개수를 저장
			}
		} catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				close(rs);
				close(pstmt);
				close(conn);
			} catch (Exception e2) {e2.printStackTrace();}
		}
		
		return count;
	}
	
	//검색전후 목록
	public List<BoardVO> getBoardList(int page, int limit, BoardVO findB ){
		List<BoardVO> blist= new ArrayList<>();
		
		try {
			conn= ds.getConnection();
			sql = "select * from (select rowNum rNum,board_no,board_name,board_title, board_hit, board_ref, board_step, board_level, board_date from (select * from boardT ";
			if(findB.getFind_field() == null) { //검색전
				sql+="";
			}
			else if(findB.getFind_field().equals("board_title")) {
				sql+=" where board_title like ?";
			}
			else if(findB.getFind_field().equals("board_cont")) {
				sql+=" where board_Cont like ?";
			}
			sql+=" order by board_ref desc, board_level asc)) where rNum >= ? and rNum<= ?";
			/* 페이징과 검색관련 쿼리문. rowNum컬럼은 오라클에서 테이블 생성시 추가해 주는 컬럼 최초 레코드 저장시 일련 번호값이 알아서 저장된다.
			 * rNum은 rowNum컬럼의 별칭이름이다.
			 * 
			 * */
			pstmt = conn.prepareStatement(sql);
			
			int startrow =(page-1)*10+1; //읽기 시작할 행 번호
			int endrow = startrow+limit-1;  //읽으르 마지막 행 번호
			
			if(findB.getFind_field() != null) { //검색하는 경우
				pstmt.setString(1,findB.getFind_name());
				pstmt.setInt(2,startrow);
				pstmt.setInt(3,endrow);
			}else { //검색하지 않는 경우
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, endrow);
			}
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO b = new BoardVO();
				b.setBoard_no(rs.getInt("board_no"));
				b.setBoard_name(rs.getString("board_name"));
				b.setBoard_title(rs.getString("board_title"));
				b.setBoard_hit(rs.getInt("board_hit"));
				b.setBoard_ref(rs.getInt("board_ref"));
				b.setBoard_step(rs.getInt("board_step"));
				b.setBoard_date(rs.getString("board_date"));
				
				blist.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
				close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return blist;
	}
	
	
	//서블릿 MVC 게시판 저장
	public void insertBoard(BoardVO b) {
		try {
			conn =ds.getConnection();
			sql ="insert into boardT ( board_no, board_name, board_title, board_pwd, board_cont, board_ref, board_step, board_level, board_date)"+
			" values(boardT_no_seq.nextval,?,?,?,?,boardT_no_seq.nextval,?,?,sysdate)";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoard_name());
			pstmt.setString(2, b.getBoard_title());
			pstmt.setString(3, b.getBoard_pwd());
			pstmt.setString(4, b.getBoard_cont());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			
			pstmt.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				close(pstmt);
				close(conn);
			} catch (Exception e2) {e2.printStackTrace();}
		}
	}
	//조회수 증가 
	public void updateHit(int board_no) {
		try {
			conn=ds.getConnection();
			sql="update boardT set board_hit = board_hit+1 where board_no=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
				close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public BoardVO getBoardCont(int board_no) {
		BoardVO bc= null;
		
		try {
			conn=ds.getConnection();
			sql="select * from boardT where board_no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				bc=new BoardVO();
				bc.setBoard_no(rs.getInt("board_no"));
				bc.setBoard_name(rs.getString("board_name"));
				bc.setBoard_title(rs.getString("board_title"));
				bc.setBoard_pwd(rs.getString("board_pwd"));
				bc.setBoard_cont(rs.getString("board_cont"));
				bc.setBoard_hit(rs.getInt("board_hit"));
				bc.setBoard_ref(rs.getInt("board_ref"));
				bc.setBoard_step(rs.getInt("board_step"));
				bc.setBoard_level(rs.getInt("board_level"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
				close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return bc;
	}
	public void replyBoard(BoardVO rb) {
		try {
			conn= ds.getConnection();
			sql = "update boardT set board_level=board_level+1 where board_ref=? and "
			+" board_level > ?";

			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, rb.getBoard_ref());
			pstmt.setInt(2, rb.getBoard_level());
			pstmt.executeUpdate();
			sql = "insert into boardT (board_no, board_name, board_title, board_pwd, board_cont, board_ref, board_step, board_level, board_date)" 
					 +"values (boardT_no_seq.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			pstmt= conn.prepareStatement(sql);

			pstmt.setString(1, rb.getBoard_name());
			pstmt.setString(2, rb.getBoard_title());
			pstmt.setString(3, rb.getBoard_pwd());
			pstmt.setString(4, rb.getBoard_cont());
			pstmt.setInt(5, rb.getBoard_ref());
			pstmt.setInt(6, rb.getBoard_step()+1);
			pstmt.setInt(7, rb.getBoard_level()+1);
			 
			 pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
				close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public void updateBoard(BoardVO eb) {
		try {
			conn=ds.getConnection();
			sql="update boardT set board_name=?, board_title=?, board_cont=? where board_no=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, eb.getBoard_name());
			pstmt.setString(2, eb.getBoard_title());
			pstmt.setString(3, eb.getBoard_cont());
			pstmt.setInt(4, eb.getBoard_no());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
				close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	//삭제 메서드
	public void deleteBoard(int board_no) {
		try {
			conn= ds.getConnection();
			sql="delete from boardT where board_no=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(pstmt);
				close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	
	
}
