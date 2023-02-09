package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.daum.vo.ProductVO;

@Controller
public class SampleController5 {

	@RequestMapping(value="/doJSON", produces="application/json")
	public @ResponseBody ProductVO doJSON() {
	 /* @ResponseBody 애노테이션을 사용하면 jsp 뷰페이지를 만들지 않고도 브라우저 출력창에 키,값 쌍의 json데이터를 쉽게 만들 수 있다.
	  * 여기서 키이름은 ProductVO 빈클래스 변수명이 된다.	
	  */
		ProductVO p=new ProductVO("포도",17000);
		return p;
	}
}
