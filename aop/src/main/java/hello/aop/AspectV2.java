package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    @Pointcut("execution(* hello.aop.order..*(..))") //porintcut expression
    private void allOrder() {
    } //pointcut signature

    @Around("allOrder()")
    private Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log v2] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
