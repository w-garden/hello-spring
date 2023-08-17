package hello;

import hello.boot.MySpringApplication;
import hello.boot.MySpringBootApplication;

/*
아래 어노테이션을 사용하면 hello 하위 클래스가 모두 componentscan 대상이됨
 */
@MySpringBootApplication
public class MySpringBootMain {
    public static void main(String[] args) {
        System.out.println("MySpringBootMain.main");
        MySpringApplication.run(MySpringBootMain.class, args);
    }
}
