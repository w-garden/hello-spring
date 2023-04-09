package com.example.springbatchtutorial.job.dbDataReadWrite;

import com.example.springbatchtutorial.core.domain.account.Accounts;
import com.example.springbatchtutorial.core.domain.account.AccountRepository;
import com.example.springbatchtutorial.core.domain.orders.OrderRepository;
import com.example.springbatchtutorial.core.domain.orders.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * desc : 주문 테이블 -> 정산 테이블 데이터 이관
 * run : --spring.batch.job.names=trMigrationJob
 */
@Configuration
@RequiredArgsConstructor
public class TrMigrationConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;

    @Bean
    public Job trMigrationJob(Step trMigrationStep){
        return jobBuilderFactory.get("trMigrationJob")
                .incrementer(new RunIdIncrementer())
                .start(trMigrationStep)
                .build();
    }
    @JobScope
    @Bean
    public Step trMigrationStep(ItemReader trOrdersReader, ItemProcessor trOrderProcessor, ItemWriter trOrdersWriter){
        return stepBuilderFactory.get("trMigrationStep")
                .<Orders, Accounts> chunk(5) //5개의 데이터 단위로 처리를 할것이고, 읽어올 데이터, 처리할 데이터 모두 Orders로 지정했다.
                .reader(trOrdersReader)
//                .writer(new ItemWriter() {
//                    @Override
//                    public void write(List items) throws Exception {
//                        items.forEach(System.out::println);
//                    }
//                })
                .processor(trOrderProcessor)
                .writer(trOrdersWriter)
                .build();
    }
//    @StepScope
//    @Bean
//    public RepositoryItemWriter<Accounts> trOrdersWriter(){
//        return new RepositoryItemWriterBuilder<Accounts>()
//                .repository(accountRepository)
//                .methodName("save")
//                .build();
//    }

    @StepScope
    @Bean
    public ItemWriter<Accounts> trOrdersWriter(){
        return new ItemWriter<Accounts>() {
            @Override
            public void write(List<? extends Accounts> items) throws Exception {
                items.forEach(item -> accountRepository.save(item));
            }
        };
    }

    @StepScope
    @Bean
    public ItemProcessor<Orders, Accounts> trOrderProcessor() {
        return new ItemProcessor<Orders, Accounts>() {
            @Override
            public Accounts process(Orders item) throws Exception {
                return new Accounts(item);
            }
        };
    }
    @StepScope
    @Bean
    public RepositoryItemReader<Orders> trOrdersReader() {
        return new RepositoryItemReaderBuilder<Orders>()
                .name("trOrdersReader")
                .repository(orderRepository)
                .methodName("findAll")
                .pageSize(5)
                .arguments(Arrays.asList())
                .sorts(Collections.singletonMap("id", Sort.Direction.DESC))
                .build();
    }

}
