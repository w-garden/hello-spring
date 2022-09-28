package net.daum.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO { //tbl_user 테이블에 메세지를 보낸  사람에게 포인터 점수 10점 업데이트
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void updatePoint(String sender, int point) {
		Map<String, Object> pm = new HashMap<String, Object>();
		pm.put("sender", sender); //보낸 사람. 키,값쌍으로 저장. point.xml에서 키 이름을 참조해서 값을 가져온다.
		pm.put("point", point); //포인터 점수
		
		this.sqlSession.update("pointUp", pm); //mybatis에서 update()메서드로 레코드를 수정. pointUp은 point.xml에서 설정할 유일한 update 아이디명
	}//메시지를 보낸 사람에게 포인터 점수 10점 업
}//메시지를 보낸 사람에게 포인터 점수 10점 업
