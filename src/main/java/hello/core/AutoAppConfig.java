package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //따로 설정하지 않으면 @ComponentScan 가 속한 패키지가 시작 위치가 됨
       // basePackages = "hello.core.member",
        //basePackageClasses = AutoAppConfig.class, //지정한 클래스의 패키지를 탐색 시작 위치로 지정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name="memoryMemberRepository")
//    MemoryMemberRepository memoryMemberRepository(){
//        return new MemoryMemberRepository();
//    }
}



//탐색위치 저장 권장방법
//패키지 위치 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다.
//최근 스프링 부트도 이 방법을 기본으로 제공한다.
//com.hello
//com.hello.serevice
//com.hello.repository 로 되어 있으면
//com.hello 에 AppConfig 같은 메인 설정 정보를 두고 @ComponentScan 애노테이션을 붙인다.


//스프링 부트에서는 @SpringBootApplication를 프로젝트의 시작 루트위치에 두는 것이 관례이다. -> 이 설정안에 @ComponentScan 이들어있음