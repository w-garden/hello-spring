package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

/**
 * JSON 형식은 보통 객체화해서 사용함
 *
 * JSON 형식 파싱을 위한 객체
 */
@Getter
@Setter
public class HelloData {
    private String username;
    private int age;
}
