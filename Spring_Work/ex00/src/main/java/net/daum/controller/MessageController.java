package net.daum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.daum.service.MessageService;
import net.daum.vo.MessageVO;

@RestController 
@RequestMapping("/message")
public class MessageController {

	
	@Autowired
	private MessageService messageService;
	
	
	//메시지 추가
	@RequestMapping(value="/addM", method=RequestMethod.POST) //post 방식 매핑 주소 처리, addM매핑주소 등록
	public ResponseEntity<String> addMessage(@RequestBody MessageVO vo){
		/* @RequestBOdy MessageVO vo는 전송되는 JSON데이터를 MessageVO 객체 타입으로 변경
		 * 
		 */
		ResponseEntity<String> entity = null;
		
		try {
			this.messageService.addMessage(vo); //메시지 추가
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK); //메시지 추가가 성공하면 'SUCCESS' 문자를 반환하고, 200정상상태 코드를 반환
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);//나쁜 상태 코드와 예외 에러 메시지 반환
		}
		return entity;
	}
}
