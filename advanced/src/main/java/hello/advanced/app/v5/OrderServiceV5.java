package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace template) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(template);
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", (TraceCallback<Void>) () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
