package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    //Servlet 코드들이 사라짐. 구현이 매우 단순해지고, 테스트코드 작성시 테스트하기 쉬움
    ModelView process(Map<String, String> paramMap);
}
