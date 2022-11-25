package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletoneTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appconfig = new AppConfig();
        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appconfig.memberService();

        //2. 조회 :  호출할 때 마다 객체를 생성
        MemberService memberService2 = appconfig.memberService();

        //참조값이 다른 것 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService ! = memberService2
        assertThat(memberService2).isNotSameAs(memberService1);

        //순수한 DI 컨테이너인 AppConfig는 요청을 할 때마다 객체를 새로 생성한다.
    }
}
