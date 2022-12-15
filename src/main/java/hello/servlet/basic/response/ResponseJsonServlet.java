package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * http://localhost:8080/response-json
 */
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("content-type", "application/json");
        //application/json 은 스펙상 utf-8형식을 사용하도록 정의. charset=utf-8 추가할 필요 없음
        resp.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("shin");
        helloData.setAge(20);


        //{"username":"shin", "age":20} 형태로 바꿔야함 -> 잭슨라이브러리 사용해서 변경
        String result = objectMapper.writeValueAsString(helloData);

        resp.getWriter().write(result);
    }
}
