package hello.proxy.app.v3;


import org.springframework.stereotype.Repository;

import static hello.proxy.util.Utility.sleep;

@Repository
public class OrderRepositoryV3 {
    public void save(String itemId) {
        //저장 로직
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }
}
