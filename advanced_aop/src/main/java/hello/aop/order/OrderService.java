package hello.aop.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public void orderItem(String itemId) {
        log.info("[OrderService] 실행");
        orderRepository.save(itemId);
    }

}
