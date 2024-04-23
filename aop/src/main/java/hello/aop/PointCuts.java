package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class PointCuts {
    //hello.aop.order 패키지와 그 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder() {
    }

    //타입 패턴이 Service
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {
    }

    //allOrder() && allService()
    @Pointcut("allOrder() && allService()")
    private void orderAndService() {
    }

}
