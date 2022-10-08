package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.BoardExDAO;
import net.daum.vo.Tbl_boardEX;

@Service
public class BoardExServiceImpl implements BoardExService {

	@Autowired
	BoardExDAO boardExDao;
	@Override
	public List<Tbl_boardEX> getALLBoard(Tbl_boardEX tb) {
		return boardExDao.getALLlist(tb);
	}
	@Override
	public int getCount() {
		return boardExDao.getCount();
	}

}
