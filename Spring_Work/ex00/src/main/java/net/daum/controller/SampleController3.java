package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.daum.vo.ProductVO;

@Controller
public class SampleController3 {

	@RequestMapping("/name_price") //name_price매핑주소 등록
	public ModelAndView name_price() {
		ModelAndView cm=new ModelAndView();
		ProductVO p=new ProductVO("노트북",1500000);
		cm.addObject("p",p);//p키이름에 p객체를 저장
		cm.setViewName("shop/nameprice");//뷰페이지 경로 설정=>/WEB-INF/views/shop/nameprice.jsp
		return cm;
	}
}
