package hello.aop.pointcut;

import hello.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Slf4j
@Import({AtTargetAtWithinTest.Config.class})
@SpringBootTest
public class AtTargetAtWithinTest {
    @Autowired
    Child child;

    @Test
    public void success() {
        log.info("child Proxy={}", child.getClass());

        child.childMethod();
        child.parentMethod();

    }

    static class Config {
        @Bean
        public Parent parent() {
            return new Parent();
        }

        @Bean
        public Child child() {
            return new Child();
        }

        @Bean
        public AtTargetAtWithinAspect atTargetAtWithinAspect() {
            return new AtTargetAtWithinAspect();
        }
    }

    static class Parent {
        public void parentMethod() {
        }
    }

    @ClassAop
    static class Child extends Parent {
        public void childMethod() {
        }
    }

    @Slf4j
    @Aspect
    static class AtTargetAtWithinAspect {
        //@target : 인스턴스를 기준으로 모든 메서드의 조인 포인트를 선정, 부모 타입의 메서드도 선정
        @Around("execution(* hello.aop..*(..)) &&@target(hello.aop.member.annotation.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        //@target : 해당 클래스  내부의 메서드만 조인 포인트를 선정, 부모 타입의 메서드는 선정 안됨
        @Around("execution(* hello.aop..*(..)) &&@within(hello.aop.member.annotation.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

}
