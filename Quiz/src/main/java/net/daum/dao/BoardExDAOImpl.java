package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.Tbl_boardEX;

@Repository
public class BoardExDAOImpl implements BoardExDAO {

	@Autowired
	SqlSession sqlSession;
	@Override
	public List<Tbl_boardEX> getALLlist(Tbl_boardEX tb) {
		return sqlSession.selectList("ex_list",tb);
	}
	@Override
	public int getCount() {
		return sqlSession.selectOne("ex_count");
	}

}
