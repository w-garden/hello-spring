package net.daum.service;

import java.util.List;

import net.daum.vo.BoardVO;

public interface BoardService {

	void insertBoard(BoardVO b);
	
	List<BoardVO> getBoardList(BoardVO b);

	int getListCount();

	BoardVO getBoardCont(int bno);

	BoardVO getBoardCont2(int bno);

	void updateBoard(BoardVO eb);

	void deleteBoard(int bno);


}
