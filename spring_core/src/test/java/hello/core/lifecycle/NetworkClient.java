package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//스프링 빈 생명주기 관리 방법
//1.인터페이스 InitializingBean, DisposableBean
// implements InitializingBean, DisposableBean
public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url +" message = " +message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }



    //스프링 빈 생명주기 관리 방법
    //3. 어노테이션 사용(스프링에서 권장)
    //    @PostConstruct,    @PreDestroy
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");

        disconnect();
    }


}
