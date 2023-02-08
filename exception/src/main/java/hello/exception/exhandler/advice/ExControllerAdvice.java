package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 여러 Controller에서 일어나는 Exception을 여기에서 처리
 * - 따로 대상을 지정하지 않으면 global 적용
 *
 *
 * @RestControllerAdvice + @ExceptionHandler 를 조합하면 예외처리를 깔끔하게 할 수 있다
 */
@Slf4j
@RestControllerAdvice("hello.exception.api")
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST) //HttpStatus 를 바꿔줌
    @ExceptionHandler(IllegalArgumentException.class)
    //@ExceptionHandler 을 선언하고, 해당 컨트롤러에서 처리하고 싶은 예외를 지정해주면 됨
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    //예외를 생략하면 메서드 파라미터의 예외가 지정된다 (컨트롤러 호출과 유사)
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
