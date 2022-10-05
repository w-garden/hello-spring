package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.dao.BbsDao;
import com.naver.vo.BbsVO;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	private BbsDao bbsDao;
	
	@Override
	public void insertBbs(BbsVO b) {
		bbsDao.insertBbs(b);
		
	}
	@Override
	public int getRowCount(BbsVO b) {
		return bbsDao.getRowCount(b);
	}

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return bbsDao.getBbsList(b);
	}

	@Transactional // 트랜잭션 적용
	@Override
	public BbsVO getBbsCont(int bbs_no) {
		this.bbsDao.updateHit(bbs_no); //조회수 증가
		return this.bbsDao.getBbs_Cont(bbs_no); //조회수 증가된 내용보기
	}//조회수 증가와 내용보기=> AOP를 통한 트랜잭션 적용

	@Override
	public BbsVO getBbsCont2(int bbs_no) {
		return this.bbsDao.getBbs_Cont(bbs_no); //조회수가 증가되지 않은 내용보기 ( 수정, 삭제시 이용)
	}//내용보기

	@Transactional //aop를 통한 트랜잭션 적용
	@Override
	public void replyBbs(BbsVO rb) {
		this.bbsDao.updateLevel(rb); //답변 레벨 증가
		this.bbsDao.replyBbs(rb);
	}//답변 레벨증가 + 답변 저장 => 트랜잭션 적용 대상

	
}
