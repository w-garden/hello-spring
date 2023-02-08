package hello.itemservice;

import hello.itemservice.web.validation.ItemValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ItemServiceApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	/** 
	 * ItemValidator 검증기를 전역적으로 사용
	 *
	 * implements WebMvcConfigurer
	 */
/*
	@Override
	public Validator getValidator() {
		return new ItemValidator();
	}
*/
	/**
	 * 스프링부트는 아래 빈을 자동으로 등록해줌
	 */
	/*
	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

		messageSource.setBasenames("messages", "errors"); //설정파일의 이름 지정
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
	 */



}
