package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring MVC의 ModelAndView 객체와 같은 역할
 */
@Getter
@Setter
public class ModelView {
    private String viewName;

    //뷰의 이름과 뷰를 렌더링 할때 필요한 model 객체
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
