package hello.advanced.trace.threadlocal;


import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static hello.advanced.util.Utility.sleep;

@Slf4j
class ThreadLocalServiceTest {
    private final ThreadLocalService service = new ThreadLocalService();

    @Test
    void threadLocal() {
        log.info("main start");
        Runnable userA = () -> service.logic("userA");
        Runnable userB = () -> service.logic("userB");

        Thread threadA = new Thread(userA);
        Thread threadB = new Thread(userB);
        threadA.setName("thread-A");
        threadB.setName("thread-B");

        threadA.start();
        sleep(100); //동시성 문제 o
        threadB.start();

        sleep(3000);
        log.info("main exit");
    }

}