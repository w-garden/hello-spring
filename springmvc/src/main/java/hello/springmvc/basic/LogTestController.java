package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass()); -->@Slf4j 로 대체 가능

    @RequestMapping("log-test")
    private String logTest(){

        String name = "Spring";
        System.out.println("name = " + name);

        //개발서버는 debug
        //운영서버는 info 단계에서 출력함
        //기본값은 info
        log.trace("trace log={}",name);

        // log.trace("trace log=" +name); //이런식으로 사용 X

        log.debug("debug log={}",name);
        log.info("info log={}",name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);

        //로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
//        log.debug("String concat log=" + name);
        return "ok";
    }
}
