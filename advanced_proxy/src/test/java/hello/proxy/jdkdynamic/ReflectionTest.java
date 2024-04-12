package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0() {
        Hello target = new Hello();
        log.info("start");
        String result1 = target.callA();
        log.info("result = {} ", result1);

        log.info("start");
        String result2 = target.callB();
        log.info("result = {} ", result2);
    }

    @Test
    void reflection1() throws Exception {

        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method callA = classHello.getMethod("callA");
        log.info("start");
        Object result1 = callA.invoke(target);
        log.info("result = {} ", result1);

        Method callB = classHello.getMethod("callB");
        log.info("start");
        Object result2 = callB.invoke(target);
        log.info("result = {} ", result2);

    }

    @Test
    void reflection2() throws Exception {
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();
        dynamicCall(classHello.getMethod("callA"), target);
        dynamicCall(classHello.getMethod("callB"), target);
    }

    private static void dynamicCall(Method methodCall, Hello target) throws IllegalAccessException, InvocationTargetException {
        log.info("start");
        Object result1 = methodCall.invoke(target);
        log.info("result = {} ", result1);
    }

    @Slf4j
    public static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
