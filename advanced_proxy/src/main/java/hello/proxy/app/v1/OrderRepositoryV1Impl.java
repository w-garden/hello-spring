package hello.proxy.app.v1;

import hello.proxy.util.Utility;

import static hello.proxy.util.Utility.sleep;

public class OrderRepositoryV1Impl implements OrderRepositoryV1 {
    @Override
    public void save(String itemId) {
        //저장 로직
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }
}
