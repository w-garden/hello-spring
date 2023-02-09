package net.daum.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.daum.vo.SampleVO;

@RestController //스프링 4.0 이후부터 이 애노테이션을 사용하면 jsp같은 뷰페이지를 만들지 않고도, Rest 방식의 데이터 처리를 위한 프로그램을 만들수 있다.
//만들어지는 데이터 객체는 JSON, 문자열, XML 이다. 결국은 해당 컨트롤러 자체가 REST API 서비스를 위한 프로그램을 만들 수 있게 된다.
public class SampleController6 {
	
	//문자열 객체 생성
	@GetMapping("lunch") //get으로 접근하는 매핑 주소, lunch매핑주소 등록
	public String lunch() {
		return "Rest api Begin~~";
	}
	
	@RequestMapping(value="/sendVO", produces="application/json") //키, 값 쌍의 json데이터가 만들어진다
	public SampleVO sendVO() {
		//리턴타입이 SampleVO 빈클래스 타입이면 해당 클래스의 변수명이 JSON데이터의 키 이름이 된다.
		
		SampleVO vo = new SampleVO();
		vo.setMno(10);
		vo.setFirstName("홍");
		vo.setLastName("길동");
		
		return vo;
	} //sendVO()
	
	@RequestMapping(value="/sendList", produces="application/json")
	public List<SampleVO> sendList(){
		List<SampleVO> list = new ArrayList<>();
		
		for(int i=1; i<=3;i++){
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("세종");
			vo.setLastName("대왕");
			
			list.add(vo);
		}
		return list;
	}// sendList()
	
	
	
	//키,값쌍의 컬렉션 Map타입 JSON
	@RequestMapping(value="/sendMap", produces="application/json")
	public Map<Integer, SampleVO> sendMap(){
		Map<Integer, SampleVO> map = new HashMap<>();
		
		for(int i=0 ; i <3; i++) {
			SampleVO vo = new SampleVO();
			
			vo.setMno(i);
			vo.setFirstName("신");
			vo.setLastName("사임당");
			
			map.put(i, vo); //컬렉션 맵에 키값 쌍으로 저장, i 값 저장은 오토박싱(기본타입이 참조타입으로 자동 형변환)
		}
		return map;
	}//sendMap();
	
	
	
	
	
	@RequestMapping("/sendError")
	public ResponseEntity<Void> sendError(){
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		/* @RestController 를 사용하면 별도의 jsp뷰페이지 파일명을 만들지 않고도 원하는 데이터를 만들 수 있다. 하지만 jsp 파일을 만들지 않다보니 
		 * 결과 데이터에 세밀한 예외적인 상황에서 문제가 발생할 수 있다. 그래서 스프링에서는 REST 서비스 API프로그램을 개발할 때 문제가 되는 
		 * 404, 500 등과 같은 Http 나쁜상태코드를 데이터와 함께 브라우저로 전송해서 좀 더 세밀한 에러제어를 할 수 있게 해준다. BAD_REQUEST는 400
		 * 나쁜 상태 코드가 브라우저로 전송한다.
		 * 
		 */
	} //sendError()
	
	//정상 json데이터와 경로에서 파일 찾을수 없음 404 나쁜 상태 코드를 함께 브라우저로 전송
	@RequestMapping(value="/sendErrorNot", produces="application/json")
	public ResponseEntity<List<SampleVO>> sendErrorNot(){
		List<SampleVO> list = new ArrayList<SampleVO>();
		
		for(int i=0;i<3;i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("강");
			vo.setLastName("감찬");
			
			list.add(vo);
		}
		return new ResponseEntity<List<SampleVO>>(list, HttpStatus.NOT_FOUND); //NOT_FOUND는 404 해당경로 파일 없음 에러
		
		
		
		
		
		
		
		
		
	}
}
