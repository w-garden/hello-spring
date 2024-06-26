package hello.proxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

import static hello.proxy.util.Utility.sleep;

@Slf4j
public class TimeProxy extends ConcreteLogic {
    private ConcreteLogic realLogic;

    public TimeProxy(ConcreteLogic realLogic) {
        this.realLogic = realLogic;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long startTime = System.currentTimeMillis();

        String result = realLogic.operation();

        long endTIme = System.currentTimeMillis();
        long resultTime = endTIme - startTime;
        log.info("TimeDecorator 종료 resultTime = {} ", resultTime);

        return result;
    }
}
