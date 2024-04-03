package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

import static hello.advanced.util.Utility.sleep;

@Slf4j
public class ThreadLocalService {
    private final ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("저장 name={} -> nameStore={}", name, nameStore.get());
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}", nameStore.get());
        return nameStore.get();
    }
}
