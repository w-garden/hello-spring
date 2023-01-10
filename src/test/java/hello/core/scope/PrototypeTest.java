package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
         AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
         //PrototypeBean.class 를지정해주면 자동으로 스프링컨테이너에 등록
        System.out.println("find PrototypeBean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find PrototypeBean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isNotSameAs(bean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        void init(){
            System.out.println("PrototypeBean.init");
        }
        @PreDestroy //프로토타입 스코프에서는 실행안됨
        void close(){
            System.out.println("PrototypeBean.close");
        }
    }
}
