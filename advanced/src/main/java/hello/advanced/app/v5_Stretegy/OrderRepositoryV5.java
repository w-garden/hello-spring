package hello.advanced.app.v5_Stretegy;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

import static hello.advanced.util.Utility.sleep;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace template) {
        this.template = new TraceTemplate(template);
    }

    public void save(String itemId) {
        template.execute("OrderRepository.save()", (TraceCallback<Void>) () -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;
        });

    }


}
