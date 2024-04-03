package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static hello.advanced.util.Utility.sleep;

@Slf4j
public class FieldServiceTest {

    private final FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> fieldService.logic("userA");
        Runnable userB = () -> fieldService.logic("userB");

        Thread threadA = new Thread(userA);
        Thread threadB = new Thread(userB);
        threadA.setName("thread-A");
        threadB.setName("thread-B");

        threadA.start();
        sleep(2000); //동시성 문제 x
//        sleep(100); //동시성 문제 o
        threadB.start();

        sleep(3000);
        log.info("main exit");
    }

}
