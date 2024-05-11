package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    /**
     * 반환 타입이 없으면서 이렇게 응답에 값을 집어넣으면, view 조회 x
     */
    @RequestMapping("/request-param-v1")     //GET, POST 둘다 조회 가능
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username =request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username,age );

        response.getWriter().write("ok");
    }

    /**
     *
     * @param memberName
     * @param memberAge
     * - 파라미터 이름으로 바인딩
     *
     * @ResponseBody 추가
     * - view 조회를 무시하고, Http message body 에 직접 해당 내용 입력
     */

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}",memberName,memberAge);

        return "ok";

    }

    /**
     *
     * @param username
     * @param age
     * Http 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username={}, age={}",username, age);
        return "ok";
    }

    /**
     *
     * @param username
     * @param age
     * String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
     *
     * @RequestParam 을 생략하면 required=false 로 적용
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}",username, age);
        return "ok";
    }

    /**
     *
     * @RequestParam.required
     * /request-param-required -> username 이 없으므로 예외
     *
     * 주의!
     * /request-param-required?username= -> 빈문자로 통과
     *
     * 주의!
     * /request-param-required
     * int age -> null 을 int 에 입력하는 것은 불가능,
     * 따라서 Integer 변경해야 함(또는 다음에 나오는 defaultValue 사용)
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, //기본값이 true
            @RequestParam(required = false) Integer age){ //int는 null이 불가하기 때문에 Integer로 변경
        log.info("username={}, age={}",username, age);
        return "ok";
    }

    /**
     * @RequestParam
     * - defaultValue 사용  *
     * 참고: defaultValue는 빈 문자의 경우에도 적용
     * * /request-param-default?username=
     */

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}",username, age);
        return "ok";
    }

    /**
     * @RequestParam Map, MultiValueMap
     * * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...])  ex) (key=userIds, value=[id1, id2])
     * */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }


    /**
     * @ModelAttribute 사용
     * 참고 : model.addAttribute(helloData) 코드도 함께 자동 적용
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        //model.addAttribute(helloData) 자동 적용
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        log.info("helloData={}", helloData); //toString 자동적용
        return "ok";
    }

    /**
     *  @ModelAttribute 생략 가능
     *
     *  SPRING의 바인딩 규칙
     *  1. String, int 같은 단순 타입 = @RequestParam
     *  2. argument resolver 로 지정해둔 타입 외 = @ModelAttribute
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }



}
