package hello.exception.serlvet;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ServletExController {

    /**
     * Exception
     */
    @GetMapping("/error-ex")
    public void errorEx(){
        throw new RuntimeException("예외 발생!");
    }

    /**
     * sendError
     */
    @GetMapping("/error-400")
    public void error400(HttpServletResponse response) throws IOException {
        response.sendError(400,"400 오류!");
    }
    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404,"404 오류!");
        // response.sendError : HTTP 상태코드와 에러메세지를 확인할 수 있음(예외를 던지지는 않는다.)
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }
}
