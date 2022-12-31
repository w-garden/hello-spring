package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
 * SQLExceptionTranslator 추가
 */

@Slf4j
public class MemberRepositoryV4_2 implements MemberRepository{

    private final DataSource dataSource;
    private final SQLErrorCodeSQLExceptionTranslator exTranslator;

    public MemberRepositoryV4_2(DataSource dataSource) {
        this.dataSource = dataSource;
        this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource); //어떤 db를 사용하는지 확인하기 위해
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(member_id, money) values(?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();

            return member;

        } catch (SQLException e) {
            throw exTranslator.translate("save", sql, e);
        } finally {
            close(conn, pstmt, null);
        }
    }


    @Override
    public Member findById(String memberId) {
        String sql = "select * from member where member_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberId=" + memberId);
            }

        } catch (SQLException e) {
            throw exTranslator.translate("findById", sql, e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void update(String memberId, int money)   {
        String sql = "update member set money=? where member_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw exTranslator.translate("update", sql, e);
        } finally {
            close(conn, pstmt, null);
        }
    }


    public void delete(String memberId) {
        String sql = "delete from member where member_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw exTranslator.translate("delete", sql, e);
        } finally {
            close(conn, pstmt, null);
        }
    }

    private void close(Connection conn, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        //주의 ! 트랜잭션 동기화를 사용하려면 DataSourceUtils 를 사용해야 한다.
        DataSourceUtils.releaseConnection(conn, dataSource);
        // releaseConnection : 트랜잭션을 사용하기 위해 동기화된 커넥션은 닫지 않고 유지
        // 트랜잭션 동기화 매니저가 관리하는 커넥션이 없는 경우 해당 커넥션을 닫는다.
    }

    private Connection getConnection() throws SQLException {
        //주의 ! 트랜잭션 동기화를 사용하려면 DataSourceUtils 를 사용해야 한다.
        Connection conn = DataSourceUtils.getConnection(dataSource);
        //getConnection : 커넥션이 있으면 그 커넥션을 반환. 없으면 새로운 커넥션 생성
        log.info("get connection={}, class={}", conn, conn.getClass());
        return conn;
    }

}
