package com.naver.service;

import java.util.List;

import com.naver.vo.BoardVO;

public interface AdminBoardService {

	int getListCount(BoardVO b);
	List<BoardVO> getBoardList(BoardVO b);
	void insertBoard(BoardVO b);
	BoardVO getAdminBoardCont(int board_no);
	void editBoard(BoardVO eb);
	void deleteBoard(int no);

}














