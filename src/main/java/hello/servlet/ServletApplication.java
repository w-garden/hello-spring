package hello.servlet;

import hello.servlet.web.springmvc.v1.SpringMemberFormControllerV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ServletComponentScan //서블릿 자동등록 어노테이션
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}


	/**
	 * 스프링 부트가 아래 정보를 자동 등록해주기 때문에
	 * application.properties 에서 등록만해주면됨
	 */
	/*
	@Bean
	ViewResolver internalResourceViewResolver(){
		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	}
	 */

	/**
	 * 아래방식으로 클래스를 스프링컨테이너에 등록가능
	 */
	/*
	@Bean
	SpringMemberFormControllerV1 springMemberFormControllerV1(){
		return new SpringMemberFormControllerV1();
	}

	 */

}
