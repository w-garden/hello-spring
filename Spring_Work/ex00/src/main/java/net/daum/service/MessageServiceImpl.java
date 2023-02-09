package net.daum.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.MessageDAO;
import net.daum.dao.PointDAO;
import net.daum.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {
   /* MessageServiceImpl에서 두개의 DAO로 나뉘어 진다. 그러므로 이부분이 스프링의 AOP를 통한 트랜잭션 적용대상이다.
    */

   
   @Autowired
   private MessageDAO messageDao;
   
   @Inject
   private PointDAO pointDao;


   //스프링의 AOP를 통한 트랜잭션 적용 대상
   @Transactional //트랜잭션 적용
   @Override
   public void addMessage(MessageVO vo) {
      this.messageDao.insertM(vo); //메세지 추가
      this.pointDao.updatePoint(vo.getSender(),10); //메세지를 보낸 사람에게 포인트 점수 10점 주기.
   }
}