package net.daum.service;

import java.util.List;

import net.daum.vo.Tbl_boardEX;

public interface BoardExService {

	List<Tbl_boardEX> getALLBoard(Tbl_boardEX tb);

	int getCount();

}
