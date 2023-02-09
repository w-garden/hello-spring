package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.daum.service.ReplyService;
import net.daum.vo.ReplyVO;

@RestController //@RestController를 추가하면 해당컨트롤러는 REST API 서비스 프로그램을 개발가능한 컨트롤러가 된다.
@RequestMapping("/replies") //컨트롤러 자페에 replies 매핑주소 등록
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	//댓글등록
	@RequestMapping(value="", method=RequestMethod.POST) //post 방식으로 접근하는 매핑주소를 처리
	public ResponseEntity<String> addReply(@RequestBody ReplyVO vo){
		/* @RequestBody ReplyVO vo 하면 전송된 json 데이터가 ReplyVO 타입의 vo 객체 타입으로 변경되어 전송된다.
		 * 
		 */
		ResponseEntity<String> entity = null;
		
		try {
			this.replyService.insertReply(vo); //댓글등록
			entity= new ResponseEntity<>("SUCCESS",HttpStatus.OK);//댓글 저장 성공시 'SUCCESS' 문자를 반환하고, HTTP 상태코드는200반환
			} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST); //예외 에러가 발생했을떄 나쁜 상태코드 문자열을 반환
		}
		return entity;
	}//addReply()
	
	
	//게시판 번호에 해당하는 댓글 목록
	@RequestMapping(value="/all/{bno}", produces="application/json")
	//매핑주소가 /all/게시판번호
	public ResponseEntity<List<ReplyVO>> replyList(@PathVariable("bno") int bno){
		/*@PathVaiable("bno")는 매핑주소인 {bno}에 입력되어진 게시판 번호값 가져오는 용도이다
		 * 
		 */
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			entity = new ResponseEntity<>(this.replyService.replyList(bno), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//replyList()
	
	
	//댓글수정
	@RequestMapping(value="/{rno}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	//PUT 은 전체자료수정, PATH는 일부자료 수정
	public ResponseEntity<String> editReply(@PathVariable("rno") int rno, @RequestBody ReplyVO vo){
		/* @PathVariable("rno")는 주소창에 입력된 {rno}댓글번호값을 추출하는 용도, @RequestBody애노테이션은 입력된 수정 JSON데이터를
		 * ReplyVO 객체타입으로 변경한다.
		 * 
		 */
		
		ResponseEntity<String> entity = null;
		
		try {
			vo.setRno(rno); //댓글번호 저장, 이유는 주소창에서 입력한 댓글번호라서, 즉 json데이터가 아니라서 @RequestBody에 의해서 ReplyVO타입으로
			//변환 못하기 때문에 코드로 setter()메서드에 저장
			this.replyService.updateReply(vo); //댓글수정
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity= new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}//editReply();
	
	
	
	
	//댓글삭제
	@RequestMapping(value="{rno}", method=RequestMethod.DELETE)
	//DELETE는 삭제시
	public ResponseEntity<String> replyDel(@PathVariable("rno") int rno){
		ResponseEntity<String> entity= null;
		
		try {
			this.replyService.delReply(rno); //댓글번호를 기준으로 삭제
			entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity= new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//relplyDel()
	
}
