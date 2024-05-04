package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import org.springframework.web.servlet.HandlerAdapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
//어댑터 패턴을 사용해서 프론트 컨트롤러가 다양한 방식의 컨트롤러를 처리 할 수 있도록 구현
public interface MyHandlerAdapter {
    boolean supports(Object handler); //handler 는 컨트롤러를 말함. 어댑터가 해당 컨트롤러를 처리할 수 있는지 판단하는 메서드
    
    ModelView handel(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException;

}
