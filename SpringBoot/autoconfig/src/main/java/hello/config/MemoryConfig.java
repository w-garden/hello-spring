package hello.config;

import memory.MemoryController;
import memory.MemoryFinder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Conditional(MemoryCondition.class)
//MemoryCondition 반환 결과가 true일 경우에만 bean등록됨


@ConditionalOnProperty(name="memory", havingValue = "sn")
public class MemoryConfig {
    @Bean
    public MemoryController memoryController(){
        return new MemoryController(memoryFinder());
    }
    @Bean
    public MemoryFinder memoryFinder(){
        return new MemoryFinder();
    }

}
