package hello.aop;


import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
//@Import(AspectV4Pointcut.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
@Import(AspectV6Advice.class)
@SpringBootTest
public class AppTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void aopInfo() {
        log.info("isAopProxy, orderService {}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository {}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    public void success() {
        orderService.order("hello");
    }

    @Test
    public void exception() {
        Assertions.assertThatThrownBy(() -> orderService.order("ex")).isInstanceOf(IllegalStateException.class);

    }

}
