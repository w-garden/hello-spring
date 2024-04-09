package hello.proxy.pureproxy.proxy.code;

import hello.proxy.util.Utility;
import lombok.extern.slf4j.Slf4j;

import static hello.proxy.util.Utility.sleep;

@Slf4j
public class RealSubject implements Subject {
    @Override
    public String operation() {
        log.info("실제 객체 호출");
        sleep(1000);
        return "data";
    }

}
