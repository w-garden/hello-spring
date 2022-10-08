package net.daum.dao;

import java.util.List;

import net.daum.vo.Tbl_boardEX;

public interface BoardExDAO {

	List<Tbl_boardEX> getALLlist(Tbl_boardEX tb);

	int getCount();

}
